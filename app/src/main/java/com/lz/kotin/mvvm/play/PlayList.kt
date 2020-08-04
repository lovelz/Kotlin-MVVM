package com.lz.kotin.mvvm.play

import android.content.Context
import com.lz.kotin.base_library.common.getRandom
import com.lz.kotin.base_library.common.isListEmpty
import com.lz.kotin.base_library.utils.toast
import com.lz.kotin.mvvm.play.bean.AudioBean

/**
 * 播放列表
 *
 * author: lovelz
 * date: on 2020-07-24
 */
class PlayList constructor(context: Context) {

    private var audioList = mutableListOf<AudioBean>()

    private var playMode = PlayMode.ORDER_PLAY_MODE

    init {
        readPlayList(context, audioList)
    }

    private var currentAudio: AudioBean? = null

    private var currentIndex = 0

    fun currentAudio(): AudioBean? {
        return currentAudio
    }

    fun setCurrentAudio(audioBean: AudioBean) {
        currentAudio = audioBean
        currentIndex = getIndexByAudio(audioBean)
    }

    /**
     * 第一次开始播放，默认播放第一个音频
     */
    fun startAudio(): AudioBean? {
        if (!isListEmpty(audioList)) {
            currentAudio = audioList[0]
        }
        return currentAudio
    }

    /**
     * 下一首
     */
    fun nextAudio(): AudioBean? {
        if (!isListEmpty(audioList)) {
            when(playMode) {
                PlayMode.ORDER_PLAY_MODE -> {
                    currentIndex = if (currentIndex < audioList.size - 1) {
                        currentIndex + 1
                    } else {
                        0
                    }
                }
                PlayMode.SINGLE_PLAY_MODE -> {}
                PlayMode.RANDOM_PLAY_MODE -> {
                    currentIndex = getRandom(0, audioList.size - 1)
                }
            }
        }
        currentAudio = audioList[currentIndex]
        return currentAudio
    }

    /**
     * 上一首
     */
    fun previousAudio(): AudioBean? {
        if (!isListEmpty(audioList)) {
            when(playMode) {
                PlayMode.ORDER_PLAY_MODE -> {
                    currentIndex = if (currentIndex == 0) {
                        audioList.size - 1
                    } else {
                        currentIndex - 1
                    }
                }
                PlayMode.SINGLE_PLAY_MODE -> {}
                PlayMode.RANDOM_PLAY_MODE -> {
                    currentIndex = getRandom(0, audioList.size - 1)
                }
            }
        }
        currentAudio = audioList[currentIndex]
        return currentAudio
    }

    fun switchPlayMode(): Int {
        when(playMode) {
            PlayMode.ORDER_PLAY_MODE -> {
                toast("单曲循环")
                playMode = PlayMode.SINGLE_PLAY_MODE
            }
            PlayMode.SINGLE_PLAY_MODE -> {
                toast("随机播放")
                playMode = PlayMode.RANDOM_PLAY_MODE
            }
            PlayMode.RANDOM_PLAY_MODE -> {
                toast("列表循环")
                playMode = PlayMode.ORDER_PLAY_MODE
            }
        }
        return playMode
    }

    fun getCurrentMode(): Int = playMode

    /**
     * 清除当前音频
     */
    fun clear() {
        currentAudio = null
    }

    private fun getIndexByAudio(audioBean: AudioBean): Int {
        for (index in 0 until audioList.size) {
            if (audioBean == audioList[index]) {
                return index
            }
        }
        return 0
    }

    fun getPlayListSize() = audioList.size

    fun getPlayList(): MutableList<AudioBean> = audioList


    class PlayMode {
        companion object {
            //播放模式
            const val ORDER_PLAY_MODE = 9
            const val SINGLE_PLAY_MODE = 99
            const val RANDOM_PLAY_MODE = 999

        }
    }

}