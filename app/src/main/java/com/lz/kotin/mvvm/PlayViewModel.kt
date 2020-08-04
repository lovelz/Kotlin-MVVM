package com.lz.kotin.mvvm

import androidx.databinding.ObservableField
import com.lz.kotin.base_library.base.BaseViewModel

/**
 * 播放音频信息共享
 *
 * author: lovelz
 * date: on 2020-07-27
 */
class PlayViewModel : BaseViewModel() {

    val songName = ObservableField<String>().apply { set("暂无播放") }

    val singer = ObservableField<String>().apply { set("") }

    val albumPic = ObservableField<Long>()

    val playStatus = ObservableField<Int>()

    val playModePic = ObservableField<Int>().apply { set(R.mipmap.play_order) }

    val playModeText = ObservableField<String>()

    val maxDuration = ObservableField<String>().apply { set("00:00") }

    val currentDuration = ObservableField<String>().apply { set("00:00") }

    val maxProgress = ObservableField<Int>()

    val playProgress = ObservableField<Int>()

}