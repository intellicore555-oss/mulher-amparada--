package com.gerenciar

import android.widget.ScrollView
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.content.ComponentName
import androidx.core.content.ContextCompat
import androidx.media3.common.MediaItem
import androidx.core.view.WindowInsetsCompat
import androidx.media3.ui.PlayerView

import java.io.File

class VisualizadorActivity : AppCompatActivity() {

    private lateinit var scrollEditor: ScrollView
private lateinit var txtEditor: TextView
private lateinit var controllerFuture: ListenableFuture<MediaController>
    private lateinit var imgVisualizador: ImageView
    private lateinit var playerView: PlayerView
    private lateinit var progresso: ProgressBar
    private lateinit var txtMensagem: TextView
private lateinit var controller: MediaController
    private lateinit var fonte: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

window.addFlags(
        WindowManager.LayoutParams.FLAG_SECURE
    )
    
        setContentView(
    R.layout.activity_visualizador
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

    if (Build.VERSION.SDK_INT >= 28) {
        navigationBarDividerColor =
            Color.TRANSPARENT
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

    // Mantém as barras do sistema visíveis
    show(WindowInsetsCompat.Type.systemBars())

    // Ícones claros
    isAppearanceLightStatusBars = false
    isAppearanceLightNavigationBars = false
}

        fonte = Typeface.createFromAsset(
            assets,
            "font.ttf"
        )

        aplicarFonte(
            findViewById(android.R.id.content)
        )

        imgVisualizador =
            findViewById(R.id.imgVisualizador)

        playerView =
            findViewById(R.id.playerView)

        progresso =
            findViewById(R.id.progresso)

        txtMensagem =
            findViewById(R.id.txtMensagem)

            txtEditor = findViewById(R.id.txtEditor)

txtEditor = findViewById(R.id.txtEditor)
scrollEditor = findViewById(R.id.scrollEditor)
        val sessionToken = SessionToken(
    this,
    ComponentName(
        this,
        MediaPlaybackService::class.java
    )
)

controllerFuture =
    MediaController.Builder(
        this,
        sessionToken
    ).buildAsync()


controllerFuture.addListener({

    controller = controllerFuture.get()

    playerView.player = controller

}, ContextCompat.getMainExecutor(this))

        val caminho =
            intent.getStringExtra("arquivo") ?: return

        val arquivo = File(caminho)

        if (!arquivo.exists()) {

            Toast.makeText(
                this,
                "Arquivo não encontrado.",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        when (arquivo.extension.lowercase()) {

    "png",
    "jpg",
    "jpeg",
    "webp",
    "gif",
    "bmp" -> {

        abrirImagem(arquivo)
    }

"mp4",
"mkv",
"avi",
"mov",
"3gp",
"webm" -> {

    abrirVideo(arquivo)
}

"mp3",
"wav",
"ogg",
"flac",
"aac",
"m4a" -> {

    abrirAudio(arquivo)
}

    "zip" -> {

        abrirZip(arquivo)
    }

    else -> {

        if (ehArquivoTexto(arquivo)) {

            abrirTexto(arquivo)

        } else {

            abrirComOutroApp(arquivo)
            finish()

        }
    }
}

}

private fun abrirVideo(arquivo: File) {

    imgVisualizador.visibility = View.GONE
    playerView.visibility = View.VISIBLE

    tocarArquivo(arquivo)
}

private fun abrirAudio(arquivo: File) {

    progresso.visibility = View.VISIBLE
    txtMensagem.visibility = View.VISIBLE

    imgVisualizador.visibility = View.VISIBLE
    playerView.visibility = View.VISIBLE

    tocarArquivo(arquivo)

    progresso.visibility = View.GONE
    txtMensagem.visibility = View.GONE
}
    private fun ehArquivoTexto(arquivo: File): Boolean {

    return try {

        val bytes = arquivo.inputStream()
            .readNBytes(4096)

        bytes.none {
            it.toInt() == 0
        }

    } catch (e: Exception) {

        false
    }
}


private fun abrirTexto(arquivo: File) {

    try {

        val texto = arquivo.readText()

        scrollEditor.visibility = View.VISIBLE

        txtEditor.text = texto

        imgVisualizador.visibility = View.GONE
        playerView.visibility = View.GONE
        txtEditor.visibility = View.VISIBLE

    } catch (e: Exception) {

        Toast.makeText(
            this,
            "Não foi possível ler o arquivo: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
    }
}

    private fun aplicarFonte(view: View) {

    if (view is TextView) {
        view.typeface = fonte
    }

    if (view is ViewGroup) {

        for (i in 0 until view.childCount) {

            aplicarFonte(
                view.getChildAt(i)
            )
        }
    }
}

private fun abrirImagem(arquivo: File) {

    progresso.visibility = View.VISIBLE
    txtMensagem.visibility = View.VISIBLE

    val bitmap = BitmapFactory.decodeFile(
        arquivo.absolutePath
    )

    imgVisualizador.setImageBitmap(bitmap)

    progresso.visibility = View.GONE
    txtMensagem.visibility = View.GONE

    imgVisualizador.visibility = View.VISIBLE
    playerView.visibility = View.GONE
}

private fun tocarArquivo(arquivo: File) {

    if (!::controller.isInitialized) {
        controllerFuture.addListener({

            controller = controllerFuture.get()

            playerView.player = controller

            val mediaItem = MediaItem.fromUri(
                Uri.fromFile(arquivo)
            )

            controller.setMediaItem(mediaItem)
            controller.prepare()
            controller.play()

        }, ContextCompat.getMainExecutor(this))

    } else {

        val mediaItem = MediaItem.fromUri(
            Uri.fromFile(arquivo)
        )

        controller.setMediaItem(mediaItem)
        controller.prepare()
        controller.play()
    }
}

private fun abrirMidia(arquivo: File) {

    progresso.visibility = View.VISIBLE
    txtMensagem.visibility = View.VISIBLE

    imgVisualizador.visibility = View.GONE
    playerView.visibility = View.VISIBLE


    if (::controller.isInitialized) {

        val mediaItem = MediaItem.fromUri(
            Uri.fromFile(arquivo)
        )

        controller.setMediaItem(mediaItem)

        controller.prepare()

        controller.play()

    }


    progresso.visibility = View.GONE
    txtMensagem.visibility = View.GONE
}

private fun abrirNoNavegador(arquivo: File) {

    val uri = FileProvider.getUriForFile(
        this,
        "$packageName.provider",
        arquivo
    )

    val mime = when (arquivo.extension.lowercase()) {
        "html", "htm" -> "text/html"
        "txt" -> "text/plain"
        else -> MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(
                arquivo.extension.lowercase()
            ) ?: "*/*"
    }

    val intent = Intent(Intent.ACTION_VIEW).apply {

        setDataAndType(
            uri,
            mime
        )

        addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }

    try {

        startActivity(intent)

    } catch (_: ActivityNotFoundException) {

        Toast.makeText(
            this,
            "Nenhum aplicativo encontrado.",
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun abrirZip(arquivo: File) {

    val uri = FileProvider.getUriForFile(
        this,
        "$packageName.provider",
        arquivo
    )

    val intent = Intent(Intent.ACTION_VIEW).apply {

        setDataAndType(
            uri,
            "application/zip"
        )

        addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }

    try {

        startActivity(intent)

    } catch (_: ActivityNotFoundException) {

        Toast.makeText(
            this,
            "Nenhum aplicativo para abrir arquivos ZIP.",
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun abrirComOutroApp(arquivo: File) {

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

        setDataAndType(
            uri,
            mime
        )

        addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }

    try {

        startActivity(
            Intent.createChooser(
                intent,
                "Abrir com"
            )
        )

    } catch (_: ActivityNotFoundException) {

        Toast.makeText(
            this,
            "Nenhum aplicativo compatível encontrado.",
            Toast.LENGTH_SHORT
        ).show()
    }
}



override fun onDestroy() {
    super.onDestroy()

    if (::controllerFuture.isInitialized) {
        MediaController.releaseFuture(controllerFuture)
    }
}

}