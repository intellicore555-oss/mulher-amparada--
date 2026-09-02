package com.gerenciar

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import android.widget.ScrollView
import android.widget.LinearLayout
import android.content.pm.PackageManager
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.provider.Settings
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.DecimalFormat
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

private lateinit var scrollView: ScrollView
    
    
    private lateinit var txtCaminho: TextView

    private lateinit var btnVoltar: ImageButton
    private lateinit var btnPesquisar: ImageButton
    private lateinit var btnMenu: ImageButton
private lateinit var txtFiltro: TextView
private lateinit var txtTipo: TextView
    private lateinit var listaArquivos: LinearLayout
private lateinit var fonte: Typeface
    private lateinit var pastaAtual: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        window.addFlags(
        WindowManager.LayoutParams.FLAG_SECURE
    )
                WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )


        WindowCompat.setDecorFitsSystemWindows(
    window,
    false
)

window.apply {

    addFlags(
        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    )

    statusBarColor = Color.TRANSPARENT
    navigationBarColor = Color.TRANSPARENT

    if (android.os.Build.VERSION.SDK_INT >= 28) {
        navigationBarDividerColor = Color.TRANSPARENT
    }

    if (android.os.Build.VERSION.SDK_INT >= 29) {
        isStatusBarContrastEnforced = false
        isNavigationBarContrastEnforced = false
    }
}

WindowInsetsControllerCompat(
    window,
    window.decorView
).apply {

    // Mantém as barras do sistema visíveis
    show(WindowInsetsCompat.Type.systemBars())

    // Ícones claros
    isAppearanceLightStatusBars = false
    isAppearanceLightNavigationBars = false
}
        
        
        txtCaminho = findViewById(R.id.txtCaminho)
txtFiltro = findViewById(R.id.txtFiltro)
txtTipo = findViewById(R.id.txtTipo)
        btnVoltar = findViewById(R.id.btnVoltar)
        btnPesquisar = findViewById(R.id.btnPesquisar)
        btnMenu = findViewById(R.id.btnMenu)

        listaArquivos = findViewById(R.id.listaArquivos)
        scrollView = findViewById(R.id.containerArquivos)

        btnVoltar.setOnClickListener {
    if (::pastaAtual.isInitialized) {
        val pastaAnterior = pastaAtual.parentFile

        if (pastaAnterior != null) {
            abrirPasta(pastaAnterior)
        }
    }
}

        abrirBiometria()
        
        
    fonte = Typeface.createFromAsset(
    assets,
    "font.ttf"
)



txtCaminho.typeface = fonte
txtFiltro.typeface = fonte
txtTipo.typeface = fonte

onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {

    override fun handleOnBackPressed() {

    if (::pastaAtual.isInitialized) {

        val raiz = Environment.getExternalStorageDirectory()

        if (pastaAtual.absolutePath != raiz.absolutePath) {

            pastaAtual.parentFile?.let {
                abrirPasta(it)
            }

            return
        }
    }

    // Se estiver na raiz, fecha a Activity
    finish()
}
})

    }

    override fun onResume() {
    super.onResume()

    if (!::pastaAtual.isInitialized) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R ||
            Environment.isExternalStorageManager()
        ) {

            pastaAtual = Environment.getExternalStorageDirectory()
            abrirPasta(pastaAtual)

        }
    }
}
    
    override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
) {

    super.onRequestPermissionsResult(
        requestCode,
        permissions,
        grantResults
    )

    if (requestCode == 100) {

        if (grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {

            pastaAtual = Environment.getExternalStorageDirectory()
            abrirPasta(pastaAtual)

        }

    }

}

    private fun pedirPermissao() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

        if (!Environment.isExternalStorageManager()) {

            try {

                val intent = Intent(
                    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                    Uri.parse("package:$packageName")
                )

                startActivity(intent)

            } catch (e: Exception) {

                val intent = Intent(
                    Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                )

                startActivity(intent)

            }

        }

    } else {

        requestPermissions(
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            100
        )

    }

}

    private fun verificarPermissao() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R &&
            !Environment.isExternalStorageManager()
        ) {

            val intent = Intent(
                Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                Uri.parse("package:$packageName")
            )

            startActivity(intent)

        } else {

            pastaAtual = Environment.getExternalStorageDirectory()
            abrirPasta(pastaAtual)

        }

}
   
    
private fun abrirPasta(pasta: File) {

    pastaAtual = pasta

    

    

    listaArquivos.removeAllViews()

listaArquivos.requestLayout()

    val arquivos = pasta.listFiles()

    if (arquivos != null) {

        arquivos.sortWith(
    compareBy<File> { !it.isDirectory }
        .thenBy {
            Regex("\\d+").replace(it.name.lowercase()) {
                it.value.padStart(10, '0')
            }
        }
)
        


        for (arquivo in arquivos) {

            val item = layoutInflater.inflate(
    R.layout.item_arquivo,
    listaArquivos,
    false
)

val imgIcone =
    item.findViewById<android.widget.ImageView>(R.id.imgIcone)

val txtNome =
    item.findViewById<TextView>(R.id.txtNome)

val txtInfo =
    item.findViewById<TextView>(R.id.txtInfo)

txtNome.typeface = fonte
txtInfo.typeface = fonte
txtNome.text = arquivo.name

            if (arquivo.isDirectory) {

                val quantidade =
                    arquivo.listFiles()?.size ?: 0

                txtInfo.text = "$quantidade itens"

                imgIcone.setImageResource(
    R.drawable.ic_folder
)

            } else {

                txtInfo.text =
                    formatarTamanho(arquivo.length())

                imgIcone.setImageResource(
    R.drawable.ic_file
                )

            }

            item.setOnClickListener {

                if (arquivo.isDirectory) {

                    abrirPasta(arquivo)

                } else {

                    abrirArquivo(arquivo)

                }

            }

            listaArquivos.addView(item)

        }

    }

    scrollView.scrollTo(0, 0)
    
    
}




private fun formatarTamanho(bytes: Long): String {

    val kb = bytes / 1024.0
    val mb = kb / 1024.0
    val gb = mb / 1024.0

    return when {

        gb >= 1 ->
            DecimalFormat("#0.00").format(gb) + " GB"

        mb >= 1 ->
            DecimalFormat("#0.00").format(mb) + " MB"

        kb >= 1 ->
            DecimalFormat("#0").format(kb) + " KB"

        else ->
            "$bytes B"

    }

}

private fun abrirArquivo(arquivo: File) {

    try {

        val intent = Intent(
            this,
            VisualizadorActivity::class.java
        )

        intent.putExtra(
            "arquivo",
            arquivo.absolutePath
        )

        startActivity(intent)

    } catch (_: Exception) {

        val uri = FileProvider.getUriForFile(
            this,
            "$packageName.provider",
            arquivo
        )

        val mime = MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(
                arquivo.extension.lowercase()
            ) ?: "*/*"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, mime)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(
            Intent.createChooser(
                intent,
                "Abrir com"
            )
        )
    }
}

private fun abrirBiometria() {


    val biometricManager = BiometricManager.from(this)

    when (
        biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_WEAK or
            BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )
    ) {

        BiometricManager.BIOMETRIC_SUCCESS -> {
            // Continua e mostra a biometria
        }

        else -> {
            // Não há biometria nem bloqueio de tela compatível
            iniciarApp()
            return
        }
    }

    val executor = ContextCompat.getMainExecutor(this)

    val biometricPrompt = BiometricPrompt(
        this,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                super.onAuthenticationSucceeded(result)
                iniciarApp()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                finish()
            }
        }
    )

    val info = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Desbloquear aplicativo")
        .setSubtitle("Use biometria ou senha do dispositivo")
        .setAllowedAuthenticators(
            BiometricManager.Authenticators.BIOMETRIC_WEAK or
            BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )
        .build()
        
        configurarBarrasTransparentes()

    biometricPrompt.authenticate(info)
}

private fun iniciarApp() {
    pedirPermissao()
}

private fun configurarBarrasTransparentes() {

    WindowCompat.setDecorFitsSystemWindows(
        window,
        false
    )

    window.apply {

        statusBarColor = Color.TRANSPARENT
        navigationBarColor = Color.TRANSPARENT

        if (Build.VERSION.SDK_INT >= 28) {
            navigationBarDividerColor = Color.TRANSPARENT
        }

        if (Build.VERSION.SDK_INT >= 29) {
            isStatusBarContrastEnforced = false
            isNavigationBarContrastEnforced = false
        }
    }

    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).apply {

        show(WindowInsetsCompat.Type.systemBars())

        isAppearanceLightStatusBars = false
        isAppearanceLightNavigationBars = false
    }
}

}
