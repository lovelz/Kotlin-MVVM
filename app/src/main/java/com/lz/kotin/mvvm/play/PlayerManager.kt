package com.lz.kotin.mvvm.play

import android.content.Context
import com.lz.kotin.mvvm.play.bean.AudioBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 音频管理
 *     通过单例模式实现,托管音频状态与信息,并且作为唯一的可信源
 *     通过观察者模式统一对状态进行分发
 *     实则是一个代理,将目标对象Player与调用者隔离,并且在内部实现了对观察者的注册与通知
 *
 * author: lovelz
 * date: on 2020-07-23
 */
class PlayerManager private constructor() : IPlayerStatus{

    companion object {
        val instance: PlayerManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PlayerManager()
        }

        /**
         * 以下为播放器状态码
         */
        const val RELEASE = 100
        const val START = 200
        const val RESUME = 300
        const val PAUSE = 400
    }

    private val observers = mutableListOf<AudioObserver>()

    private val playerHelper: IPlayer = MediaPlayerHelper()

    private var disposable: Disposable? = null

    private var playStatus = RELEASE

    /**
     * 播放列表
     */
    private lateinit var playList: PlayList

    fun init(context: Context) {
        playList = PlayList(context)
        playerHelper.setPlayStatus(this)
        startTimer()
    }

    /**
     * 定时器，用于每1000ms更新一次进度
     */
    private fun startTimer() {
        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                if (playerHelper.isPlaying()) {
                    sendProgressToObserver(playerHelper.getProgress())
                }
            }
    }

    fun controlPlay() {
        if (playList.currentAudio() == null) {
            start()
        } else {
            if (playerHelper.isPlaying()) {
                pause()
            } else {
                resume()
            }
        }
    }

    private fun start() {
        playList.startAudio()?.let {
            play(it)
        }
    }

    fun next() {
        playList.nextAudio()?.let {
            play(it)
        }
    }

    fun previous() {
        playList.previousAudio()?.let {
            play(it)
        }
    }

    fun play(audioBean: AudioBean) {
        playStatus = START
        playList.setCurrentAudio(audioBean)
        audioBean.path?.let { playerHelper.play(it) }
        sendAudioToObserver(audioBean)
        sendPlayStatusToObserver()
    }

    private fun resume() {
        playStatus = RESUME
        playerHelper.resume()
        sendPlayStatusToObserver()
    }

    private fun pause() {
        playStatus = PAUSE
        playerHelper.pause()
        sendPlayStatusToObserver()
    }

    fun seekTo(duration: Int) {
        playerHelper.seekTo(duration)
    }

    fun switchPlayMode() {
        sendPlayModeToObserver(playList.switchPlayMode())
    }

    fun getCurrentAudioBean(): AudioBean? {
        return playList.currentAudio()
    }

    fun getPlayListSize(): Int? {
        return playList.getPlayListSize()
    }

    fun getPlayList(): MutableList<AudioBean> {
        return playList.getPlayList()
    }

    fun clear() {
        disposable?.dispose()
        playList.clear()
        playerHelper.reset()
        playerHelper.release()
        playList.clear()
    }

    fun register(audioObserver: AudioObserver) {
        observers.add(audioObserver)
        notifyObserver(audioObserver)
    }

    fun unregister(audioObserver: AudioObserver) {
        observers.remove(audioObserver)
    }

    private fun notifyObserver(audioObserver: AudioObserver) {
        playList.currentAudio()?.let {
            audioObserver.onAudioBean(it)
        }
        audioObserver.onPlayMode(playList.getCurrentMode())
        audioObserver.onPlayStatus(playStatus)
        playList.currentAudio()?.duration?.let {
            audioObserver.onProgress(
                playerHelper.getProgress(), it
            )
        }
    }

    private fun sendAudioToObserver(audioBean: AudioBean) {
        observers.forEach {
            it.onAudioBean(audioBean)
        }
    }

    private fun sendPlayStatusToObserver() {
        observers.forEach {
            it.onPlayStatus(playStatus)
        }
    }

    /**
     * 给观察者发送进度数据
     */
    private fun sendProgressToObserver(duration: Int) {
        observers.forEach {
            playList.currentAudio()?.duration?.let { it1 -> it.onProgress(duration, it1) }
        }
    }

    private fun sendPlayModeToObserver(playMode: Int) {
        observers.forEach {
            it.onPlayMode(playMode)
        }
    }

    override fun onBufferingUpdate(percent: Int) {

    }

    override fun onComplete() {
        playList.nextAudio()?.let {
            play(it)
        }
    }
}