package com.mulheres

import android.app.*
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*
import kotlin.math.abs
import kotlin.math.max


class PalmaService : Service() {

    private var contadorPalmas = 0
    private var recorder: AudioRecord? = null
    private var rodando = true
    private var ultimaPalma: Long = 0


    override fun onCreate() {
        super.onCreate()

        criarCanal()
        iniciarForeground()
        iniciarDeteccao()
    }


    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    private fun criarCanal() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val canal = NotificationChannel(
                "palmas",
                "Proteção por Palmas",
                NotificationManager.IMPORTANCE_LOW
            )

            val manager =
                getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(canal)
        }
    }


    private fun iniciarForeground() {

        val intent = Intent(
            this,
            MainActivity::class.java
        )

        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )


        val notification =
            NotificationCompat.Builder(
                this,
                "palmas"
            )
                .setContentTitle(
                    "Proteção por Palmas"
                )
                .setContentText(
                    "Escuta ativa habilitada"
                )
                .setSmallIcon(
                    android.R.drawable.ic_btn_speak_now
                )
                .setContentIntent(
                    pendingIntent
                )
                .setOngoing(true)
                .build()


        startForeground(
            2,
            notification
        )
    }


    private fun iniciarDeteccao() {

        Thread {

            detectarPalmas()

        }.start()

    }
    
        private fun detectarPalmas() {

        try {

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                stopSelf()
                return
            }


            val taxa = 44100

            val bufferSize =
                AudioRecord.getMinBufferSize(
                    taxa,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT
                )


            if (bufferSize <= 0) {
                stopSelf()
                return
            }


            recorder = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                taxa,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
            )


            if (
                recorder?.state != AudioRecord.STATE_INITIALIZED
            ) {
                stopSelf()
                return
            }


            val buffer = ShortArray(bufferSize)


            recorder?.startRecording()


            while (rodando) {

                val leitura =
                    recorder?.read(
                        buffer,
                        0,
                        buffer.size
                    ) ?: 0


                if (leitura <= 0)
                    continue


                var pico = 0


                for (i in 0 until leitura) {

                    val valor =
                        abs(buffer[i].toInt())

                    pico = max(
                        pico,
                        valor
                    )
                }


                // Sensibilidade da palma
                if (pico > 14000) {

                    val agora =
                        System.currentTimeMillis()


                    if (
                        agora - ultimaPalma < 1500
                    ) {

                        contadorPalmas++

                    } else {

                        contadorPalmas = 1
                    }


                    ultimaPalma = agora


                    if (contadorPalmas >= 3) {

                        contadorPalmas = 0

                        acionarEmergencia()
                    }
                }
            }


        } catch (e: Exception) {

            e.printStackTrace()

        } finally {

            try {
                recorder?.stop()
            } catch (_: Exception) {
            }


            try {
                recorder?.release()
            } catch (_: Exception) {
            }


            recorder = null
        }
    }
    
        private fun acionarEmergencia() {

        try {

            val intent = Intent(
                Intent.ACTION_CALL
            ).apply {

                data = Uri.parse(
                    "tel:180"
                )

                flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK
            }


            startActivity(intent)


        } catch (e: Exception) {

            e.printStackTrace()


            try {

                val intent = Intent(
                    Intent.ACTION_DIAL
                ).apply {

                    data = Uri.parse(
                        "tel:180"
                    )

                    flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK
                }


                startActivity(intent)


            } catch (_: Exception) {

            }
        }
    }



    override fun onDestroy() {

        rodando = false


        try {

            recorder?.stop()

        } catch (_: Exception) {

        }


        try {

            recorder?.release()

        } catch (_: Exception) {

        }


        recorder = null


        super.onDestroy()
    }

}