package com.mulheres

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.media3.ui.DefaultTimeBar
import androidx.media3.ui.PlayerView

import com.google.common.util.concurrent.ListenableFuture

import java.io.File

class VisualizadorActivity : AppCompatActivity() {

    private lateinit var scrollEditor: ScrollView
    private lateinit var txtEditor: TextView

    private lateinit var controllerFuture:
        ListenableFuture<MediaController>

    private lateinit var controller: MediaController

    private lateinit var imgVisualizador: ImageView
    private lateinit var playerView: PlayerView

    private lateinit var progresso: ProgressBar
    private lateinit var txtMensagem: TextView

    private lateinit var playerControls: View

    private lateinit var timeBar: DefaultTimeBar

    private lateinit var txtPosicao: TextView
    private lateinit var txtDuracao: TextView

    private lateinit var btnAnterior: ImageButton
    private lateinit var btnVoltar: ImageButton
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnAvancar: ImageButton
    private lateinit var btnProximo: ImageButton
    private lateinit var btnRepetir: ImageButton
    private lateinit var btnAleatorio: ImageButton

    private lateinit var fonte: Typeface

    private lateinit var arquivoAtual: File

    private val handler =
        Handler(Looper.getMainLooper())

    private var controlesVisiveis = false

    private val esconderControlesRunnable =
        Runnable {
            esconderControles()
        }

    private val atualizarTempoRunnable =
        object : Runnable {

            override fun run() {

                if (::controller.isInitialized) {

                    val posicao =
                        controller.currentPosition

                    val duracao =
                        controller.duration

                    txtPosicao.text =
                        formatarTempo(posicao)

                    if (duracao >= 0) {

                        txtDuracao.text =
                            formatarTempo(duracao)
                    }

                    atualizarBotoes()
                    atualizarTimeBar()
                }

                handler.postDelayed(
                    this,
                    500
                )
            }
        }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

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

            statusBarColor =
                Color.TRANSPARENT

            navigationBarColor =
                Color.TRANSPARENT

            if (
                android.os.Build.VERSION.SDK_INT >= 28
            ) {

                navigationBarDividerColor =
                    Color.TRANSPARENT
            }

            if (
                android.os.Build.VERSION.SDK_INT >= 29
            ) {

                isStatusBarContrastEnforced =
                    false

                isNavigationBarContrastEnforced =
                    false
            }
        }

        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).apply {

            show(
                WindowInsetsCompat.Type.systemBars()
            )

            isAppearanceLightStatusBars =
                false

            isAppearanceLightNavigationBars =
                false
        }

        fonte =
            Typeface.createFromAsset(
                assets,
                "font.ttf"
            )

        aplicarFonte(
            findViewById(android.R.id.content)
        )

        inicializarViews()

        configurarControles()

        configurarToqueVideo()

        iniciarController()
    }

    private fun inicializarViews() {

        imgVisualizador =
            findViewById(
                R.id.imgVisualizador
            )

        playerView =
            findViewById(
                R.id.playerView
            )

        progresso =
            findViewById(
                R.id.progresso
            )

        txtMensagem =
            findViewById(
                R.id.txtMensagem
            )

        scrollEditor =
            findViewById(
                R.id.scrollEditor
            )

        txtEditor =
            findViewById(
                R.id.txtEditor
            )

        playerControls =
            findViewById(
                R.id.playerControls
            )

        timeBar =
            findViewById(
                R.id.exo_progress
            )

        txtPosicao =
            findViewById(
                R.id.txtPosicao
            )

        txtDuracao =
            findViewById(
                R.id.txtDuracao
            )

        btnAnterior =
            findViewById(
                R.id.btnAnterior
            )

        btnVoltar =
            findViewById(
                R.id.btnVoltar
            )

        btnPlayPause =
            findViewById(
                R.id.btnPlayPause
            )

        btnAvancar =
            findViewById(
                R.id.btnAvancar
            )

        btnProximo =
            findViewById(
                R.id.btnProximo
            )

        btnRepetir =
            findViewById(
                R.id.btnRepetir
            )

        btnAleatorio =
            findViewById(
                R.id.btnAleatorio
            )

        playerControls.alpha =
            0f

        playerControls.visibility =
            View.GONE
    }

    private fun iniciarController() {

        val sessionToken =
            SessionToken(
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

            try {

                controller =
                    controllerFuture.get()

                playerView.player =
                    controller

                configurarListener()

                val caminho =
                    intent.getStringExtra(
                        "arquivo"
                    )

                if (
                    caminho != null
                ) {

                    val arquivo =
                        File(caminho)

                    if (
                        arquivo.exists()
                    ) {

                        arquivoAtual =
                            arquivo

                        abrirArquivo(
                            arquivo
                        )
                    }
                }

                handler.post(
                    atualizarTempoRunnable
                )

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "Erro ao iniciar o player.",
                    Toast.LENGTH_LONG
                ).show()
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun configurarListener() {

        controller.addListener(
            object : Player.Listener {

                override fun onIsPlayingChanged(
                    isPlaying: Boolean
                ) {

                    atualizarBotoes()
                }

                override fun onPlaybackStateChanged(
                    playbackState: Int
                ) {

                    atualizarBotoes()
                }

                override fun onMediaItemTransition(
                    mediaItem: MediaItem?,
                    reason: Int
                ) {

                    atualizarBotoes()
                }
            }
        )
    }

    private fun configurarControles() {

        /*
         * PLAY / PAUSE
         *
         * É UM ÚNICO BOTÃO.
         */

        btnPlayPause.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            if (controller.isPlaying) {

                controller.pause()

            } else {

                controller.play()
            }

            mostrarControles()
        }

        /*
         * VOLTAR 10 SEGUNDOS
         */

        btnVoltar.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            controller.seekBack()

            mostrarControles()
        }

        /*
         * AVANÇAR 10 SEGUNDOS
         */

        btnAvancar.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            controller.seekForward()

            mostrarControles()
        }

        /*
         * ANTERIOR
         */

        btnAnterior.setOnClickListener {

            irParaArquivoAnterior()

            mostrarControles()
        }

        /*
         * PRÓXIMO
         */

        btnProximo.setOnClickListener {

            irParaProximoArquivo()

            mostrarControles()
        }

        /*
         * REPETIR
         *
         * OFF
         * ↓
         * REPETIR UMA
         * ↓
         * REPETIR TODOS
         * ↓
         * OFF
         */

        btnRepetir.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            controller.repeatMode =
                when (controller.repeatMode) {

                    Player.REPEAT_MODE_OFF ->
                        Player.REPEAT_MODE_ONE

                    Player.REPEAT_MODE_ONE ->
                        Player.REPEAT_MODE_ALL

                    else ->
                        Player.REPEAT_MODE_OFF
                }

            atualizarBotoes()

            mostrarControles()
        }

        /*
         * ALEATÓRIO
         */

        btnAleatorio.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            controller.shuffleModeEnabled =
                !controller.shuffleModeEnabled

            atualizarBotoes()

            mostrarControles()
        }
    }

    private fun configurarToqueVideo() {

        playerView.setOnTouchListener {
                _,
                event ->

            if (
                event.action ==
                MotionEvent.ACTION_UP
            ) {

                if (
                    controlesVisiveis
                ) {

                    esconderControles()

                } else {

                    mostrarControles()
                }
            }

            true
        }
    }

    /*
     * =========================================================
     * CONTROLES COM FADE
     * =========================================================
     */

    private fun mostrarControles() {

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        if (
            playerControls.visibility ==
            View.VISIBLE &&
            controlesVisiveis
        ) {

            playerControls.animate()
                .cancel()

        } else {

            playerControls.alpha =
                0f

            playerControls.visibility =
                View.VISIBLE

            playerControls.animate()
                .alpha(1f)
                .setDuration(220)
                .start()
        }

        controlesVisiveis =
            true

        handler.postDelayed(
            esconderControlesRunnable,
            3500
        )
    }

    private fun esconderControles() {

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        if (
            playerControls.visibility !=
            View.VISIBLE
        ) {

            controlesVisiveis =
                false

            return
        }

        playerControls.animate()
            .alpha(0f)
            .setDuration(220)
            .withEndAction {

                playerControls.visibility =
                    View.GONE

                playerControls.alpha =
                    0f

                controlesVisiveis =
                    false
            }
            .start()
    }

    /*
     * =========================================================
     * PLAY / PAUSE
     * =========================================================
     */

    private fun atualizarBotoes() {

        if (!::controller.isInitialized) {
            return
        }

        if (controller.isPlaying) {

            btnPlayPause.setImageResource(
                R.drawable.exo_styled_controls_pause
            )

            btnPlayPause.contentDescription =
                "Pausar"

        } else {

            btnPlayPause.setImageResource(
                R.drawable.exo_styled_controls_play
            )

            btnPlayPause.contentDescription =
                "Reproduzir"
        }

        /*
         * ANTERIOR
         */

        btnAnterior.alpha =
            if (
                existeArquivoAnterior()
            ) {

                1f

            } else {

                0.45f
            }

        /*
         * PRÓXIMO
         */

        btnProximo.alpha =
            if (
                existeArquivoProximo()
            ) {

                1f

            } else {

                0.45f
            }

        /*
         * ALEATÓRIO
         */

        btnAleatorio.alpha =
            if (
                controller.shuffleModeEnabled
            ) {

                1f

            } else {

                0.5f
            }

        /*
         * REPETIÇÃO
         */

        btnRepetir.alpha =
            when (
                controller.repeatMode
            ) {

                Player.REPEAT_MODE_ONE ->
                    1f

                Player.REPEAT_MODE_ALL ->
                    1f

                else ->
                    0.5f
            }
    }

    /*
     * =========================================================
     * PRÓXIMO ARQUIVO
     * =========================================================
     */

    private fun irParaProximoArquivo() {

        val proximo =
            encontrarArquivoVizinho(
                +1
            )

        if (
            proximo != null
        ) {

            abrirArquivoDeMidia(
                proximo
            )

        } else {

            Toast.makeText(
                this,
                "Não há outro arquivo de mídia.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
     * =========================================================
     * ARQUIVO ANTERIOR
     * =========================================================
     */

    private fun irParaArquivoAnterior() {

        val anterior =
            encontrarArquivoVizinho(
                -1
            )

        if (
            anterior != null
        ) {

            abrirArquivoDeMidia(
                anterior
            )

        } else {

            Toast.makeText(
                this,
                "Não há outro arquivo de mídia.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
     * =========================================================
     * PROCURA ARQUIVO VIZINHO
     * =========================================================
     */

    private fun encontrarArquivoVizinho(
        direcao: Int
    ): File? {

        if (
            !::arquivoAtual.isInitialized
        ) {
            return null
        }

        val pasta =
            arquivoAtual.parentFile
                ?: return null

        val arquivos =
            pasta.listFiles()
                ?.filter {
                    it.isFile &&
                    ehMidia(it)
                }
                ?.sortedBy {
                    it.name.lowercase()
                }
                ?: return null

        if (
            arquivos.isEmpty()
        ) {
            return null
        }

        val indice =
            arquivos.indexOfFirst {
                it.absolutePath ==
                arquivoAtual.absolutePath
            }

        if (
            indice == -1
        ) {
            return null
        }

        var novoIndice =
            indice + direcao

        /*
         * ALEATÓRIO
         */

        if (
            ::controller.isInitialized &&
            controller.shuffleModeEnabled
        ) {

            val candidatos =
                arquivos.filter {
                    it.absolutePath !=
                    arquivoAtual.absolutePath
                }

            if (
                candidatos.isEmpty()
            ) {
                return null
            }

            return candidatos.random()
        }

        /*
         * REPETIÇÃO DE TODOS
         */

        if (
            novoIndice >= arquivos.size &&
            ::controller.isInitialized &&
            controller.repeatMode ==
            Player.REPEAT_MODE_ALL
        ) {

            novoIndice = 0
        }

        if (
            novoIndice < 0 &&
            ::controller.isInitialized &&
            controller.repeatMode ==
            Player.REPEAT_MODE_ALL
        ) {

            novoIndice =
                arquivos.lastIndex
        }

        if (
            novoIndice !in
            arquivos.indices
        ) {

            return null
        }

        return arquivos[novoIndice]
    }

    private fun existeArquivoAnterior():
        Boolean {

        if (
            !::arquivoAtual.isInitialized
        ) {
            return false
        }

        val pasta =
            arquivoAtual.parentFile
                ?: return false

        val arquivos =
            pasta.listFiles()
                ?.filter {
                    it.isFile &&
                    ehMidia(it)
                }
                ?.sortedBy {
                    it.name.lowercase()
                }
                ?: return false

        val indice =
            arquivos.indexOfFirst {
                it.absolutePath ==
                arquivoAtual.absolutePath
            }

        return indice > 0 ||
            (
                ::controller.isInitialized &&
                controller.repeatMode ==
                Player.REPEAT_MODE_ALL &&
                arquivos.size > 1
            )
    }

    private fun existeArquivoProximo():
        Boolean {

        if (
            !::arquivoAtual.isInitialized
        ) {
            return false
        }

        val pasta =
            arquivoAtual.parentFile
                ?: return false

        val arquivos =
            pasta.listFiles()
                ?.filter {
                    it.isFile &&
                    ehMidia(it)
                }
                ?.sortedBy {
                    it.name.lowercase()
                }
                ?: return false

        val indice =
            arquivos.indexOfFirst {
                it.absolutePath ==
                arquivoAtual.absolutePath
            }

        return indice >= 0 &&
            (
                indice < arquivos.lastIndex ||
                (
                    ::controller.isInitialized &&
                    controller.repeatMode ==
                    Player.REPEAT_MODE_ALL &&
                    arquivos.size > 1
                )
            )
    }

    private fun abrirArquivoDeMidia(
        arquivo: File
    ) {

        if (
            !arquivo.exists()
        ) {
            return
        }

        arquivoAtual =
            arquivo

        imgVisualizador.visibility =
            View.GONE

        scrollEditor.visibility =
            View.GONE

        playerView.visibility =
            View.VISIBLE

        playerControls.visibility =
            View.VISIBLE

        playerControls.alpha =
            1f

        val uri =
            FileProvider.getUriForFile(
                this,
                "$packageName.provider",
                arquivo
            )

        val mediaItem =
            MediaItem.fromUri(
                uri
            )

        controller.setMediaItem(
            mediaItem
        )

        controller.prepare()
        controller.play()

        atualizarBotoes()

        mostrarControles()
    }

    /*
     * =========================================================
     * ABERTURA DO ARQUIVO
     * =========================================================
     */

    private fun abrirArquivo(
        arquivo: File
    ) {

        when (
            arquivo.extension.lowercase()
        ) {

            "png",
            "jpg",
            "jpeg",
            "webp",
            "gif",
            "bmp" -> {

                abrirImagem(
                    arquivo
                )
            }

            "mp4",
            "mkv",
            "avi",
            "mov",
            "3gp",
            "webm" -> {

                abrirVideo(
                    arquivo
                )
            }

            "mp3",
            "wav",
            "ogg",
            "flac",
            "aac",
            "m4a" -> {

                abrirAudio(
                    arquivo
                )
            }

            "zip" -> {

                abrirZip(
                    arquivo
                )
            }

            else -> {

                if (
                    ehArquivoTexto(
                        arquivo
                    )
                ) {

                    abrirTexto(
                        arquivo
                    )

                } else {

                    abrirComOutroApp(
                        arquivo
                    )

                    finish()
                }
            }
        }
    }

    private fun abrirVideo(
        arquivo: File
    ) {

        arquivoAtual =
            arquivo

        imgVisualizador.visibility =
            View.GONE

        scrollEditor.visibility =
            View.GONE

        playerView.visibility =
            View.VISIBLE

        playerControls.visibility =
            View.VISIBLE

        tocarArquivo(
            arquivo
        )

        mostrarControles()
    }

    private fun abrirAudio(
        arquivo: File
    ) {

        arquivoAtual =
            arquivo

        imgVisualizador.visibility =
            View.GONE

        scrollEditor.visibility =
            View.GONE

        playerView.visibility =
            View.VISIBLE

        playerControls.visibility =
            View.VISIBLE

        tocarArquivo(
            arquivo
        )

        mostrarControles()
    }

    private fun tocarArquivo(
        arquivo: File
    ) {

        if (
            !::controller.isInitialized
        ) {
            return
        }

        val uri =
            FileProvider.getUriForFile(
                this,
                "$packageName.provider",
                arquivo
            )

        val mediaItem =
            MediaItem.fromUri(
                uri
            )

        controller.setMediaItem(
            mediaItem
        )

        controller.prepare()
        controller.play()

        atualizarBotoes()
    }

    /*
     * =========================================================
     * TIME BAR
     * =========================================================
     */

    private fun atualizarTimeBar() {

        if (
            !::controller.isInitialized
        ) {
            return
        }

        val duracao =
            controller.duration

        if (
            duracao <= 0
        ) {
            return
        }

        timeBar.setDuration(
            duracao
        )

        timeBar.setPosition(
            controller.currentPosition
        )

        timeBar.setBufferedPosition(
            controller.bufferedPosition
        )
    }

    /*
     * =========================================================
     * IMAGEM
     * =========================================================
     */

    private fun abrirImagem(
        arquivo: File
    ) {

        progresso.visibility =
            View.VISIBLE

        txtMensagem.visibility =
            View.VISIBLE

        playerView.visibility =
            View.GONE

        playerControls.visibility =
            View.GONE

        val bitmap =
            BitmapFactory.decodeFile(
                arquivo.absolutePath
            )

        imgVisualizador.setImageBitmap(
            bitmap
        )

        imgVisualizador.visibility =
            View.VISIBLE

        progresso.visibility =
            View.GONE

        txtMensagem.visibility =
            View.GONE
    }

    /*
     * =========================================================
     * TEXTO
     * =========================================================
     */

    private fun ehArquivoTexto(
        arquivo: File
    ): Boolean {

        return try {

            val bytes =
                arquivo.inputStream()
                    .readNBytes(
                        4096
                    )

            bytes.none {
                it.toInt() == 0
            }

        } catch (
            e: Exception
        ) {

            false
        }
    }

    private fun abrirTexto(
        arquivo: File
    ) {

        try {

            val texto =
                arquivo.readText()

            playerView.visibility =
                View.GONE

            playerControls.visibility =
                View.GONE

            imgVisualizador.visibility =
                View.GONE

            scrollEditor.visibility =
                View.VISIBLE

            txtEditor.visibility =
                View.VISIBLE

            txtEditor.text =
                texto

        } catch (
            e: Exception
        ) {

            Toast.makeText(
                this,
                "Não foi possível ler o arquivo: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /*
     * =========================================================
     * FONTE
     * =========================================================
     */

    private fun aplicarFonte(
        view: View
    ) {

        if (
            view is TextView
        ) {

            view.typeface =
                fonte
        }

        if (
            view is ViewGroup
        ) {

            for (
                i in 0 until view.childCount
            ) {

                aplicarFonte(
                    view.getChildAt(i)
                )
            }
        }
    }

    /*
     * =========================================================
     * TEMPO
     * =========================================================
     */

    private fun formatarTempo(
        millis: Long
    ): String {

        if (
            millis < 0
        ) {

            return "00:00"
        }

        val totalSegundos =
            millis / 1000

        val segundos =
            totalSegundos % 60

        val minutos =
            (totalSegundos / 60) % 60

        val horas =
            totalSegundos / 3600

        return if (
            horas > 0
        ) {

            String.format(
                "%d:%02d:%02d",
                horas,
                minutos,
                segundos
            )

        } else {

            String.format(
                "%02d:%02d",
                minutos,
                segundos
            )
        }
    }

    /*
     * =========================================================
     * TIPOS DE MÍDIA
     * =========================================================
     */

    private fun ehMidia(
        arquivo: File
    ): Boolean {

        return when (
            arquivo.extension.lowercase()
        ) {

            "mp3",
            "wav",
            "ogg",
            "flac",
            "aac",
            "m4a",
            "mp4",
            "mkv",
            "avi",
            "mov",
            "3gp",
            "webm" -> true

            else -> false
        }
    }

    /*
     * =========================================================
     * ZIP
     * =========================================================
     */

    private fun abrirZip(
        arquivo: File
    ) {

        val uri =
            FileProvider.getUriForFile(
                this,
                "$packageName.provider",
                arquivo
            )

        val intent =
            Intent(
                Intent.ACTION_VIEW
            ).apply {

                setDataAndType(
                    uri,
                    "application/zip"
                )

                addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }

        try {

            startActivity(
                intent
            )

        } catch (
            _: ActivityNotFoundException
        ) {

            Toast.makeText(
                this,
                "Nenhum aplicativo para abrir arquivos ZIP.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
     * =========================================================
     * OUTRO APLICATIVO
     * =========================================================
     */

    private fun abrirComOutroApp(
        arquivo: File
    ) {

        val uri =
            FileProvider.getUriForFile(
                this,
                "$packageName.provider",
                arquivo
            )

        val mime =
            MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(
                    arquivo.extension.lowercase()
                )
                ?: "*/*"

        val intent =
            Intent(
                Intent.ACTION_VIEW
            ).apply {

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

        } catch (
            _: ActivityNotFoundException
        ) {

            Toast.makeText(
                this,
                "Nenhum aplicativo compatível encontrado.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
     * =========================================================
     * DESTROY
     * =========================================================
     */

    override fun onDestroy() {

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        handler.removeCallbacks(
            atualizarTempoRunnable
        )

        if (
            ::controller.isInitialized
        ) {

            controller.release()
        }

        if (
            ::controllerFuture.isInitialized
        ) {

            MediaController.releaseFuture(
                controllerFuture
            )
        }

        super.onDestroy()
    }
}