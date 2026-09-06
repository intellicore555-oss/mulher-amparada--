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
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val proximitySensor =
        sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    private var sensorAtivo = false
    private var estavaPerto = false

    @JavascriptInterface
    fun iniciarSensorProximidade(): Boolean {

        if (proximitySensor == null) {
            return false
        }

        if (!sensorAtivo) {

            sensorManager.registerListener(
                this,
                proximitySensor,
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
        estavaPerto = false
    }

    @JavascriptInterface
    fun sensorProximidadeDisponivel(): Boolean {
        return proximitySensor != null
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event == null) return

        val distancia = event.values[0]

        // Sensor de proximidade:
        // true  = perto
        // false = longe
        val estaPerto =
            distancia < proximitySensor!!.maximumRange

        /*
         * Executa somente na transição:
         *
         * LONGE -> PERTO
         *
         * Enquanto continuar perto, não dispara novamente.
         */
        if (estaPerto && !estavaPerto) {

            val intent = Intent(
                context,
                MyDeviceAdminReceiver::class.java
            )

            intent.action = "com.mulheres.BLOQUEAR_CELULAR"

            context.sendBroadcast(intent)
        }

        estavaPerto = estaPerto
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int
    ) {
    }
}