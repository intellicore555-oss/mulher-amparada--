package com.gerenciar

import android.app.PendingIntent
import android.content.Intent
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService


class MediaPlaybackService : MediaSessionService() {

    private lateinit var player: ExoPlayer
    private lateinit var mediaSession: MediaSession


    override fun onCreate() {
        super.onCreate()


        player = ExoPlayer.Builder(this)
            .setHandleAudioBecomingNoisy(true)
            .build()


        val intent = Intent(
            this,
            VisualizadorActivity::class.java
        )


        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )


        mediaSession = MediaSession.Builder(
            this,
            player
        )
            .setSessionActivity(pendingIntent)
            .build()
    }


    override fun onGetSession(
        controllerInfo: MediaSession.ControllerInfo
    ): MediaSession {

        return mediaSession
    }


    override fun onDestroy() {

        mediaSession.release()

        player.release()

        super.onDestroy()
    }
}