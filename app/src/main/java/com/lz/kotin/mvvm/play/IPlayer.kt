package com.lz.kotin.mvvm.play

/**
 * 所有的具体Player必须实现该接口，目的是为了让PlayerManager不依赖任何具体的音频
 * 播放实现，原因如下：
 *
 *    a:PlayerManager包含业务信息，Player不应该与业务信息进行耦合，否则每次改动都对业务造成影响
 *    b:符合开闭原则，如果需要对Player进行特换势必会牵扯到PlayerManager中的业务，因而造成不必要的麻烦，
 *        如果基于IPlayer编程，扩展出一个Player即可，对扩展开放，修改关闭
 *
 * author: lovelz
 * date: on 2020-07-24
 */
interface IPlayer {

    /**
     * 将IPlayerStatus注入到PlayController内，做状态监听
     */
    fun setPlayStatus(iPlayerStatus: IPlayerStatus)

    /**
     * 播放新的音频
     */
    fun play(path: String)

    /**
     * 播放
     */
    fun resume()

    /**
     * 暂停
     */
    fun pause()

    /**
     * 停止播放
     */
    fun stop()

    /**
     * 跳转播放
     */
    fun seekTo(duration: Int)

    /**
     * 重置
     */
    fun reset()

    /**
     * 释放内存，播放器置空
     */
    fun release()

    /**
     * 是否正在播放
     */
    fun isPlaying(): Boolean

    /**
     * 获取播放进度
     */
    fun getProgress(): Int

}