package com.lz.kotin.mvvm.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lz.kotin.base_library.utils.setNoRepeatClick
import com.lz.kotin.mvvm.R
import com.lz.kotin.mvvm.play.AudioObserver
import com.lz.kotin.mvvm.play.PlayList
import com.lz.kotin.mvvm.play.PlayerManager
import com.lz.kotin.mvvm.play.bean.AudioBean
import kotlinx.android.synthetic.main.fragment_play_list.*

/**
 * author: lovelz
 * date: on 2020-07-31
 */
class PlayListFragment : DialogFragment(), AudioObserver {

    private val adapter by lazy { AudioAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Dialog_FullScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PlayerManager.instance.register(this)
        clickEvent()
        tvListSize.text = String.format("(%s)", PlayerManager.instance.getPlayListSize())
        rvPlayList.adapter = adapter
    }

    private fun clickEvent() {
        setNoRepeatClick(root, ivPlayMode, tvPlayMode) {
            when(it.id) {
                R.id.root -> dismiss()
                R.id.ivPlayMode, R.id.tvPlayMode -> PlayerManager.instance.switchPlayMode()
            }
        }
    }

    override fun onAudioBean(audioBean: AudioBean) {
        super.onAudioBean(audioBean)
        adapter.updateCurrentPlaying()
    }

    override fun onPlayMode(playMode: Int) {
        when(playMode) {
            PlayList.PlayMode.ORDER_PLAY_MODE -> {
                ivPlayMode.setImageResource(R.mipmap.play_order_gray)
                tvPlayMode.text = "列表循环"
            }
            PlayList.PlayMode.SINGLE_PLAY_MODE -> {
                ivPlayMode.setImageResource(R.mipmap.play_single_gray)
                tvPlayMode.text = "单曲循环"
            }
            PlayList.PlayMode.RANDOM_PLAY_MODE -> {
                ivPlayMode.setImageResource(R.mipmap.play_random_gray)
                tvPlayMode.text = "随机播放"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PlayerManager.instance.unregister(this)
    }

}