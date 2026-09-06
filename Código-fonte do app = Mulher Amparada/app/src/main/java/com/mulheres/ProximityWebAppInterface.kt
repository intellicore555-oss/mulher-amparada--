package com.mulheres

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.webkit.JavascriptInterface

class ProximityWebAppInterface(
    private val context: Context
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(
            Context.SENSOR_SERVICE
        ) as SensorManager

    private val lightSensor =
        sensorManager.getDefaultSensor(
            Sensor.TYPE_LIGHT
        )

    private var sensorAtivo = false
    private var estavaCoberto = false

    /*
     * Valor em lux.
     *
     * Abaixo desse valor consideramos
     * que o sensor foi coberto.
     */
    private val LIMITE_LUX = 5f

    @JavascriptInterface
    fun iniciarSensorProximidade(): Boolean {

        if (lightSensor == null) {
            return false
        }

        if (!sensorAtivo) {

            sensorManager.registerListener(
                this,
                lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            sensorAtivo = true
        }

        return true
    }

    @JavascriptInterface
    fun pararSensorProximidade() {

        sensorManager.unregisterListener(this)

        sensorAtivo = false
        estavaCoberto = false
    }

    @JavascriptInterface
    fun sensorProximidadeDisponivel(): Boolean {
        return lightSensor != null
    }

    override fun onSensorChanged(
        event: SensorEvent?
    ) {

        if (event == null) return

        val luminosidade = event.values[0]

        /*
         * Quanto menor a luminosidade,
         * mais provavelmente o sensor está coberto.
         */
        val estaCoberto =
            luminosidade <= LIMITE_LUX

        /*
         * Executa somente na transição:
         *
         * DESCOBERTO -> COBERTO
         */
        if (estaCoberto && !estavaCoberto) {

            val intent = Intent(
                context,
                MyDeviceAdminReceiver::class.java
            )

            intent.action =
                "com.mulheres.BLOQUEAR_CELULAR"

            context.sendBroadcast(intent)
        }

        estavaCoberto = estaCoberto
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int
    ) {
    }
}