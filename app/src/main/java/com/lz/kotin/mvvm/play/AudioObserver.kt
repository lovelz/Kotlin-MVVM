package com.lz.kotin.mvvm.play

import com.lz.kotin.mvvm.play.bean.AudioBean

/**
 * 音频播放观察者对象，可实时观察音频信息、播放状态、播放进度
 *
 * author: lovelz
 * date: on 2020-07-24
 */
interface AudioObserver {

    /**
     * 播放音频信息
     */
    fun onAudioBean(audioBean: AudioBean) {}

    /**
     * 播放状态
     */
    fun onPlayStatus(playStatus: Int) {}

    /**
     * 播放进度信息
     */
    fun onProgress(currentDuration: Int, totalDuration: Int) {}

    /**
     * 播放模式
     */
    fun onPlayMode(playMode: Int)

}