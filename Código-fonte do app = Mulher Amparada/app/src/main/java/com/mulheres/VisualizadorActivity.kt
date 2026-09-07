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
import androidx.media3.ui.TimeBar
import androidx.media3.ui.PlayerView

import com.google.common.util.concurrent.ListenableFuture

import java.io.File
import java.util.Locale

class VisualizadorActivity : AppCompatActivity() {

    private lateinit var scrollEditor: ScrollView
    private lateinit var txtEditor: TextView

    private lateinit var controllerFuture: ListenableFuture<MediaController>
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

    private var arquivoAtual: File? = null

    private val handler = Handler(Looper.getMainLooper())

    private val esconderControlesRunnable = Runnable {
        esconderControles()
    }

    private val atualizarTempoRunnable = object : Runnable {

        override fun run() {

            if (::controller.isInitialized) {

                val posicao = controller.currentPosition
                val duracao = controller.duration

                txtPosicao.text = formatarTempo(posicao)

                if (duracao >= 0) {
                    txtDuracao.text = formatarTempo(duracao)
                } else {
                    txtDuracao.text = "00:00"
                }

                if (!estaArrastandoBarra) {
                    atualizarBarra()
                }

                atualizarBotoes()
            }

            handler.postDelayed(this, 500)
        }
    }

    private var estaArrastandoBarra = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        setContentView(R.layout.activity_visualizador)

        WindowCompat.setDecorFitsSystemWindows(window, false)

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

            show(WindowInsetsCompat.Type.systemBars())

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

        imgVisualizador = findViewById(R.id.imgVisualizador)

        playerView = findViewById(R.id.playerView)

        progresso = findViewById(R.id.progresso)

        txtMensagem = findViewById(R.id.txtMensagem)

        scrollEditor = findViewById(R.id.scrollEditor)

        txtEditor = findViewById(R.id.txtEditor)

        playerControls = findViewById(R.id.playerControls)

        timeBar = findViewById(R.id.exo_progress)

        txtPosicao = findViewById(R.id.txtPosicao)

        txtDuracao = findViewById(R.id.txtDuracao)

        btnAnterior = findViewById(R.id.btnAnterior)

        btnVoltar = findViewById(R.id.btnVoltar)

        btnPlayPause = findViewById(R.id.btnPlayPause)

        btnAvancar = findViewById(R.id.btnAvancar)

        btnProximo = findViewById(R.id.btnProximo)

        btnRepetir = findViewById(R.id.btnRepetir)

        btnAleatorio = findViewById(R.id.btnAleatorio)

        prepararControles()

        configurarToqueVideo()

        configurarBotoes()

        configurarBarraDeProgresso()

        esconderControlesImediatamente()

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

            try {

                controller = controllerFuture.get()

                playerView.player = controller

                configurarListener()

                val caminho =
                    intent.getStringExtra("arquivo")

                if (caminho != null) {

                    val arquivo = File(caminho)

                    if (arquivo.exists()) {

                        abrirArquivo(arquivo)

                    } else {

                        Toast.makeText(
                            this,
                            "Arquivo não encontrado.",
                            Toast.LENGTH_LONG
                        ).show()
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

    private fun prepararControles() {

        playerControls.alpha = 0f
        playerControls.visibility = View.INVISIBLE
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
                    atualizarBarra()
                }

                override fun onMediaItemTransition(
                    mediaItem: MediaItem?,
                    reason: Int
                ) {

                    atualizarBotoes()
                    atualizarBarra()
                }

                override fun onRepeatModeChanged(
                    repeatMode: Int
                ) {

                    atualizarBotaoRepetir()
                }

                override fun onShuffleModeEnabledChanged(
                    shuffleModeEnabled: Boolean
                ) {

                    atualizarBotaoAleatorio()
                }
            }
        )
    }

    private fun configurarBotoes() {

        /*
         * PLAY / PAUSE
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
         * VOLTAR ALGUNS SEGUNDOS
         */

        btnVoltar.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            controller.seekBack()

            mostrarControles()
        }

        /*
         * AVANÇAR ALGUNS SEGUNDOS
         */

        btnAvancar.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            controller.seekForward()

            mostrarControles()
        }

        /*
         * ARQUIVO ANTERIOR
         */

        btnAnterior.setOnClickListener {

            val arquivo = arquivoAtual
                ?: return@setOnClickListener

            irParaArquivoAnterior(arquivo)

            mostrarControles()
        }

        /*
         * PRÓXIMO ARQUIVO
         */

        btnProximo.setOnClickListener {

            val arquivo = arquivoAtual
                ?: return@setOnClickListener

            irParaProximoArquivo(arquivo)

            mostrarControles()
        }

        /*
         * REPETIR
         *
         * OFF -> UM -> TODOS -> OFF
         */

        btnRepetir.setOnClickListener {

            if (!::controller.isInitialized) {
                return@setOnClickListener
            }

            when (controller.repeatMode) {

                Player.REPEAT_MODE_OFF -> {

                    controller.repeatMode =
                        Player.REPEAT_MODE_ONE
                }

                Player.REPEAT_MODE_ONE -> {

                    controller.repeatMode =
                        Player.REPEAT_MODE_ALL
                }

                else -> {

                    controller.repeatMode =
                        Player.REPEAT_MODE_OFF
                }
            }

            atualizarBotaoRepetir()

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

            atualizarBotaoAleatorio()

            mostrarControles()
        }
    }

    private fun configurarBarraDeProgresso() {

        timeBar.addListener(
            object : TimeBar.OnScrubListener {

                override fun onScrubStarted(
                    timeBar: TimeBar,
                    position: Long
                ) {

                    estaArrastandoBarra = true

                    mostrarControles()
                }

                override fun onScrubMove(
                    timeBar: TimeBar,
                    position: Long
                ) {

                    txtPosicao.text =
                        formatarTempo(position)
                }

                override fun onScrubStop(
                    timeBar: TimeBar,
                    position: Long,
                    canceled: Boolean
                ) {

                    estaArrastandoBarra = false

                    if (!canceled &&
                        ::controller.isInitialized
                    ) {

                        controller.seekTo(position)
                    }

                    mostrarControles()
                }
            }
        )
    }

    private fun configurarToqueVideo() {

        playerView.setOnTouchListener { _, event ->

            if (event.action == MotionEvent.ACTION_UP) {

                if (playerControls.visibility ==
                    View.VISIBLE
                ) {

                    esconderControles()

                } else {

                    mostrarControles()
                }
            }

            true
        }

        /*
         * O overlay não bloqueia os cliques
         * dos ImageButtons e da barra.
         */

        playerControls.setOnTouchListener { _, event ->

            if (event.action ==
                MotionEvent.ACTION_DOWN
            ) {

                mostrarControles()
            }

            false
        }
    }

    private fun mostrarControles() {

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        playerControls.animate().cancel()

        if (playerControls.visibility != View.VISIBLE) {

            playerControls.visibility =
                View.VISIBLE

            playerControls.alpha = 0f

            playerControls.animate()
                .alpha(1f)
                .setDuration(220)
                .start()

        } else {

            playerControls.animate()
                .alpha(1f)
                .setDuration(120)
                .start()
        }

        handler.postDelayed(
            esconderControlesRunnable,
            3500
        )
    }

    private fun esconderControles() {

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        if (playerControls.visibility !=
            View.VISIBLE
        ) {
            return
        }

        playerControls.animate().cancel()

        playerControls.animate()
            .alpha(0f)
            .setDuration(220)
            .withEndAction {

                /*
                 * INVISIBLE em vez de GONE.
                 *
                 * Isso evita qualquer alteração
                 * de layout quando os controles
                 * desaparecem.
                 */

                playerControls.visibility =
                    View.INVISIBLE
            }
            .start()
    }

    private fun esconderControlesImediatamente() {

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        playerControls.animate().cancel()

        playerControls.alpha = 0f

        playerControls.visibility =
            View.INVISIBLE
    }

    private fun atualizarBotoes() {

        if (!::controller.isInitialized) {
            return
        }

        /*
         * UM ÚNICO BOTÃO:
         * PLAY <-> PAUSE
         */

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
         * BOTÃO ANTERIOR
         */

        btnAnterior.isEnabled =
            arquivoAtual?.let {
                encontrarArquivoAnterior(it) != null
            } ?: false

        /*
         * BOTÃO PRÓXIMO
         */

        btnProximo.isEnabled =
            arquivoAtual?.let {
                encontrarProximoArquivo(it) != null
            } ?: false

        atualizarBotaoRepetir()

        atualizarBotaoAleatorio()

        atualizarBarra()
    }

    private fun atualizarBotaoRepetir() {

        if (!::controller.isInitialized) {
            return
        }

        when (controller.repeatMode) {

            Player.REPEAT_MODE_OFF -> {

                btnRepetir.setImageResource(
                    R.drawable.ic_repeat
                )

                btnRepetir.alpha = 0.55f
            }

            Player.REPEAT_MODE_ONE -> {

                btnRepetir.setImageResource(
                    R.drawable.ic_repeat_one
                )

                btnRepetir.alpha = 1f
            }

            Player.REPEAT_MODE_ALL -> {

                btnRepetir.setImageResource(
                    R.drawable.ic_repeat
                )

                btnRepetir.alpha = 1f
            }
        }
    }

    private fun atualizarBotaoAleatorio() {

        if (!::controller.isInitialized) {
            return
        }

        if (controller.shuffleModeEnabled) {

            btnAleatorio.alpha = 1f

        } else {

            btnAleatorio.alpha = 0.55f
        }
    }

    private fun atualizarBarra() {

        if (!::controller.isInitialized) {
            return
        }

        val duracao = controller.duration

        if (duracao <= 0) {
            return
        }

        timeBar.setDuration(duracao)

        timeBar.setPosition(
            controller.currentPosition
        )

        timeBar.setBufferedPosition(
            controller.bufferedPosition
        )
    }

    private fun abrirArquivo(
        arquivo: File
    ) {

        arquivoAtual = arquivo

        val extensao =
            arquivo.extension.lowercase(Locale.getDefault())

        when {

            ehImagem(extensao) -> {

                abrirImagem(arquivo)
            }

            ehVideo(extensao) -> {

                abrirVideo(arquivo)
            }

            ehAudio(extensao) -> {

                abrirAudio(arquivo)
            }

            ehTexto(extensao) -> {

                abrirTexto(arquivo)
            }

            ehZip(extensao) -> {

                abrirZip(arquivo)
            }

            else -> {

                abrirComOutroApp(arquivo)
            }
        }
    }

    private fun abrirVideo(
        arquivo: File
    ) {

        imgVisualizador.visibility =
            View.GONE

        scrollEditor.visibility =
            View.GONE

        txtMensagem.visibility =
            View.GONE

        progresso.visibility =
            View.GONE

        playerView.visibility =
            View.VISIBLE

        playerControls.visibility =
            View.INVISIBLE

        tocarArquivo(arquivo)
    }

    private fun abrirAudio(
        arquivo: File
    ) {

        imgVisualizador.visibility =
            View.GONE

        scrollEditor.visibility =
            View.GONE

        progresso.visibility =
            View.GONE

        playerView.visibility =
            View.VISIBLE

        txtMensagem.visibility =
            View.VISIBLE

        txtMensagem.text =
            arquivo.name

        tocarArquivo(arquivo)
    }

    private fun tocarArquivo(
        arquivo: File
    ) {

        if (!::controller.isInitialized) {
            return
        }

        try {

            val uri =
                FileProvider.getUriForFile(
                    this,
                    "${packageName}.fileprovider",
                    arquivo
                )

            val mediaItem =
                MediaItem.fromUri(uri)

            controller.setMediaItem(
                mediaItem
            )

            controller.prepare()

            controller.play()

            atualizarBotoes()

        } catch (e: Exception) {

            Toast.makeText(
                this,
                "Não foi possível reproduzir o arquivo.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun abrirImagem(
        arquivo: File
    ) {

        playerView.visibility =
            View.GONE

        playerControls.visibility =
            View.INVISIBLE

        scrollEditor.visibility =
            View.GONE

        progresso.visibility =
            View.VISIBLE

        txtMensagem.visibility =
            View.GONE

        try {

            val bitmap =
                BitmapFactory.decodeFile(
                    arquivo.absolutePath
                )

            if (bitmap != null) {

                imgVisualizador.visibility =
                    View.VISIBLE

                imgVisualizador.setImageBitmap(
                    bitmap
                )

            } else {

                imgVisualizador.visibility =
                    View.GONE

                txtMensagem.visibility =
                    View.VISIBLE

                txtMensagem.text =
                    "Não foi possível abrir a imagem."
            }

        } catch (e: Exception) {

            imgVisualizador.visibility =
                View.GONE

            txtMensagem.visibility =
                View.VISIBLE

            txtMensagem.text =
                "Não foi possível abrir a imagem."
        }
    }

    private fun abrirTexto(
        arquivo: File
    ) {

        playerView.visibility =
            View.GONE

        imgVisualizador.visibility =
            View.GONE

        playerControls.visibility =
            View.INVISIBLE

        progresso.visibility =
            View.GONE

        txtMensagem.visibility =
            View.GONE

        scrollEditor.visibility =
            View.VISIBLE

        try {

            txtEditor.text =
                arquivo.readText()

        } catch (e: Exception) {

            txtEditor.text =
                "Não foi possível ler o arquivo."
        }
    }

    private fun abrirZip(
        arquivo: File
    ) {

        playerView.visibility =
            View.GONE

        imgVisualizador.visibility =
            View.GONE

        playerControls.visibility =
            View.INVISIBLE

        scrollEditor.visibility =
            View.GONE

        progresso.visibility =
            View.GONE

        txtMensagem.visibility =
            View.VISIBLE

        txtMensagem.text =
            "Arquivo ZIP: ${arquivo.name}"
    }

    private fun abrirComOutroApp(
        arquivo: File
    ) {

        try {

            val uri =
                FileProvider.getUriForFile(
                    this,
                    "${packageName}.fileprovider",
                    arquivo
                )

            val extensao =
                arquivo.extension.lowercase(
                    Locale.getDefault()
                )

            val mime =
                MimeTypeMap.getSingleton()
                    .getMimeTypeFromExtension(
                        extensao
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

            startActivity(intent)

        } catch (e: ActivityNotFoundException) {

            Toast.makeText(
                this,
                "Nenhum aplicativo pode abrir este arquivo.",
                Toast.LENGTH_LONG
            ).show()

        } catch (e: Exception) {

            Toast.makeText(
                this,
                "Não foi possível abrir o arquivo.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /*
     * =========================================================
     * NAVEGAÇÃO ENTRE ARQUIVOS DA MESMA PASTA
     * =========================================================
     */

    private fun obterArquivosDeMidia(
        arquivo: File
    ): List<File> {

        val pasta =
            arquivo.parentFile
                ?: return emptyList()

        if (!pasta.exists() ||
            !pasta.isDirectory
        ) {
            return emptyList()
        }

        return pasta
            .listFiles()
            ?.filter { file ->

                file.isFile &&
                    ehMidia(file)

            }
            ?.sortedBy {

                it.name.lowercase(
                    Locale.getDefault()
                )
            }
            ?: emptyList()
    }

    private fun encontrarArquivoAnterior(
        arquivo: File
    ): File? {

        val arquivos =
            obterArquivosDeMidia(arquivo)

        val indice =
            arquivos.indexOfFirst {

                it.absolutePath ==
                    arquivo.absolutePath
            }

        if (indice <= 0) {
            return null
        }

        return arquivos[indice - 1]
    }

    private fun encontrarProximoArquivo(
        arquivo: File
    ): File? {

        val arquivos =
            obterArquivosDeMidia(arquivo)

        val indice =
            arquivos.indexOfFirst {

                it.absolutePath ==
                    arquivo.absolutePath
            }

        if (indice == -1 ||
            indice >= arquivos.lastIndex
        ) {
            return null
        }

        return arquivos[indice + 1]
    }

    private fun irParaArquivoAnterior(
        arquivo: File
    ) {

        val anterior =
            encontrarArquivoAnterior(arquivo)

        if (anterior != null) {

            abrirArquivoDeMidia(
                anterior
            )

        } else {

            Toast.makeText(
                this,
                "Este é o primeiro arquivo da pasta.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun irParaProximoArquivo(
        arquivo: File
    ) {

        if (::controller.isInitialized &&
            controller.shuffleModeEnabled
        ) {

            val arquivos =
                obterArquivosDeMidia(arquivo)

            val outros =
                arquivos.filter {

                    it.absolutePath !=
                        arquivo.absolutePath
                }

            if (outros.isNotEmpty()) {

                val proximo =
                    outros.random()

                abrirArquivoDeMidia(
                    proximo
                )

                return
            }
        }

        val proximo =
            encontrarProximoArquivo(arquivo)

        if (proximo != null) {

            abrirArquivoDeMidia(
                proximo
            )

        } else {

            Toast.makeText(
                this,
                "Este é o último arquivo da pasta.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun abrirArquivoDeMidia(
        arquivo: File
    ) {

        arquivoAtual = arquivo

        val extensao =
            arquivo.extension.lowercase(
                Locale.getDefault()
            )

        if (ehVideo(extensao)) {

            abrirVideo(arquivo)

        } else if (ehAudio(extensao)) {

            abrirAudio(arquivo)
        }
    }

    /*
     * =========================================================
     * TIPOS DE ARQUIVO
     * =========================================================
     */

    private fun ehMidia(
        arquivo: File
    ): Boolean {

        val extensao =
            arquivo.extension.lowercase(
                Locale.getDefault()
            )

        return ehVideo(extensao) ||
            ehAudio(extensao)
    }

    private fun ehVideo(
        extensao: String
    ): Boolean {

        return extensao in setOf(
            "mp4",
            "mkv",
            "webm",
            "avi",
            "mov",
            "3gp",
            "m4v",
            "ts",
            "flv"
        )
    }

    private fun ehAudio(
        extensao: String
    ): Boolean {

        return extensao in setOf(
            "mp3",
            "wav",
            "ogg",
            "oga",
            "m4a",
            "aac",
            "flac",
            "opus",
            "amr",
            "3gp"
        )
    }

    private fun ehImagem(
        extensao: String
    ): Boolean {

        return extensao in setOf(
            "jpg",
            "jpeg",
            "png",
            "gif",
            "webp",
            "bmp",
            "heic",
            "heif"
        )
    }

    private fun ehTexto(
        extensao: String
    ): Boolean {

        return extensao in setOf(
            "txt",
            "kt",
            "java",
            "xml",
            "html",
            "css",
            "js",
            "json",
            "md",
            "csv"
        )
    }

    private fun ehZip(
        extensao: String
    ): Boolean {

        return extensao in setOf(
            "zip",
            "rar",
            "7z",
            "tar",
            "gz"
        )
    }

    /*
     * =========================================================
     * TEMPO
     * =========================================================
     */

    private fun formatarTempo(
        milissegundos: Long
    ): String {

        if (milissegundos < 0) {
            return "00:00"
        }

        val segundosTotal =
            milissegundos / 1000

        val segundos =
            segundosTotal % 60

        val minutos =
            (segundosTotal / 60) % 60

        val horas =
            segundosTotal / 3600

        return if (horas > 0) {

            String.format(
                Locale.getDefault(),
                "%02d:%02d:%02d",
                horas,
                minutos,
                segundos
            )

        } else {

            String.format(
                Locale.getDefault(),
                "%02d:%02d",
                minutos,
                segundos
            )
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

        when (view) {

            is TextView -> {

                view.typeface = fonte
            }
        }

        if (view is ViewGroup) {

            for (i in 0 until view.childCount) {

                aplicarFonte(
                    view.getChildAt(i)
                )
            }
        }
    }

    /*
     * =========================================================
     * CICLO DE VIDA
     * =========================================================
     */

    override fun onPause() {
        super.onPause()

        if (::controller.isInitialized) {

            controller.pause()
        }
    }

    override fun onDestroy() {

        handler.removeCallbacks(
            atualizarTempoRunnable
        )

        handler.removeCallbacks(
            esconderControlesRunnable
        )

        if (::controllerFuture.isInitialized) {

            MediaController.releaseFuture(
                controllerFuture
            )
        }

        super.onDestroy()
    }
}