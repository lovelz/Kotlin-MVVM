package com.lz.kotin.mvvm.ui.play

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lz.kotin.mvvm.R
import com.lz.kotin.mvvm.play.PlayerManager
import com.lz.kotin.mvvm.play.bean.AudioBean

/**
 * author: lovelz
 * date: on 2020-07-31
 */
class AudioAdapter : BaseQuickAdapter<AudioBean, BaseViewHolder>(R.layout.layout_audio_item) {

    private var currentPosition = getCurrentPlayPosition()

    init {
        setNewData(PlayerManager.instance.getPlayList())
        setOnItemClickListener{_, _, position ->
            PlayerManager.instance.play(data[position])
        }
    }

    private fun getCurrentPlayPosition(): Int {
        PlayerManager.instance.getPlayList().apply {
            for (position in 0 until size) {
                if (this[position].id == PlayerManager.instance.getCurrentAudioBean()?.id) {
                    return position
                }
            }
        }
        return 0
    }

    override fun convert(helper: BaseViewHolder, item: AudioBean) {
        if (item.id == PlayerManager.instance.getCurrentAudioBean()?.id) {
            helper.getView<View>(R.id.tvSongName).isSelected = true
            helper.getView<View>(R.id.tvSinger).isSelected = true
            helper.setGone(R.id.ivPlaying, true)
        } else {
            helper.getView<View>(R.id.tvSongName).isSelected = false
            helper.getView<View>(R.id.tvSinger).isSelected = false
            helper.setGone(R.id.ivPlaying, false)
        }
        helper.setText(R.id.tvSongName, item.name)
        helper.setText(R.id.tvSinger, "-${item.singer}")
    }

    fun updateCurrentPlaying() {
        val playPosition = getCurrentPlayPosition()
        notifyItemChanged(playPosition)
        if (playPosition != currentPosition) {
            notifyItemChanged(currentPosition)
        }
        currentPosition = playPosition
    }
}