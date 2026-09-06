package com.mulheres

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent

class MyDeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        super.onReceive(context, intent)

        if (intent.action != "com.mulheres.BLOQUEAR_CELULAR") {
            return
        }

        val devicePolicyManager =
            context.getSystemService(
                Context.DEVICE_POLICY_SERVICE
            ) as DevicePolicyManager

        val adminComponent =
            android.content.ComponentName(
                context,
                MyDeviceAdminReceiver::class.java
            )

        // Só pode bloquear se o administrador
        // do dispositivo estiver ativado.
        if (devicePolicyManager.isAdminActive(adminComponent)) {

            devicePolicyManager.lockNow()
        }
    }
}