package com.lz.kotin.mvvm.play

/**
 * 播放状态
 *
 * author: lovelz
 * date: on 2020-07-24
 */
interface IPlayerStatus {

    /**
     * 缓冲更新
     */
    fun onBufferingUpdate(percent: Int)

    /**
     * 播放结束
     */
    fun onComplete()

}