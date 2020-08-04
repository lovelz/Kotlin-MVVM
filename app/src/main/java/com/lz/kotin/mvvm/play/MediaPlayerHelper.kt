package com.lz.kotin.mvvm.play

import android.media.MediaPlayer
import android.media.MediaPlayer.*
import android.widget.Toast
import com.bumptech.glide.Glide.init
import com.lz.kotin.base_library.utils.toast

/**
 * MediaPlayer音频播放工具类
 *
 * author: lovelz
 * date: on 2020-07-24
 */
class MediaPlayerHelper : IPlayer,
    OnCompletionListener,
    OnBufferingUpdateListener,
    OnErrorListener,
    OnPreparedListener {

    private val mediaPlayer by lazy { MediaPlayer() }
    private var iPlayerStatus: IPlayerStatus? = null

    init {
        //各种状态监听
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.setOnBufferingUpdateListener(this)
        mediaPlayer.setOnErrorListener(this)
        mediaPlayer.setOnPreparedListener(this)
    }

    override fun setPlayStatus(iPlayerStatus: IPlayerStatus) {
        this.iPlayerStatus = iPlayerStatus
    }

    override fun play(path: String) {
        mediaPlayer.reset()
        kotlin.runCatching {
            mediaPlayer.setDataSource(path)
        }.onSuccess {
            mediaPlayer.prepare()
        }.onFailure {
            toast("无效文件")
        }
    }

    override fun resume() {
        mediaPlayer.start()
    }

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun stop() {
        mediaPlayer.stop()
    }

    override fun seekTo(duration: Int) {
        mediaPlayer.seekTo(duration)
    }

    override fun reset() {
        mediaPlayer.reset()
    }

    override fun release() {
        mediaPlayer.release()
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    override fun getProgress(): Int {
        return mediaPlayer.currentPosition
    }

    override fun onCompletion(mp: MediaPlayer?) {
        iPlayerStatus?.onComplete()
    }

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        iPlayerStatus?.onBufferingUpdate(percent)
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return true
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
    }

}