package com.lz.kotin.mvvm.ui.play

import android.os.Bundle
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.gyf.immersionbar.ktx.statusBarHeight
import com.lz.kotin.base_library.base.BaseVmFragment
import com.lz.kotin.base_library.base.DataBindingConfig
import com.lz.kotin.base_library.common.stringForTime
import com.lz.kotin.base_library.utils.setNoRepeatClick
import com.lz.kotin.base_library.utils.toast
import com.lz.kotin.mvvm.BR
import com.lz.kotin.mvvm.PlayViewModel
import com.lz.kotin.mvvm.R
import com.lz.kotin.mvvm.play.PlayerManager
import com.lz.kotin.mvvm.ui.PlayBindingAdapter
import kotlinx.android.synthetic.main.fragment_player.*

/**
 * author: lovelz
 * date: on 2020-07-30
 */
class PlayerFragment : BaseVmFragment() {

    private var playVM: PlayViewModel? = null
    private val playListFragment = PlayListFragment()

    override fun initViewModel() {
        playVM = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun initEvent(savedInstanceState: Bundle?) {
        val params = ivBack.layoutParams as ConstraintLayout.LayoutParams
        params.topMargin = statusBarHeight
        ivBack.layoutParams = params

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                PlayerManager.instance.seekTo(seekBar!!.progress)
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvStartTime.text = stringForTime(seekBar!!.progress)
            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_player

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_player, playVM)
            .addBindingParam(BR.vm, playVM)
    }

    override fun onClick() {
        setNoRepeatClick(ivBack, ivMode, ivPrevious, ivNext, ivPlay, ivList) {
            when(it.id) {
                R.id.ivBack -> nav().navigateUp()
                R.id.ivMode -> PlayerManager.instance.switchPlayMode()
                R.id.ivPrevious -> PlayerManager.instance.previous()
                R.id.ivNext -> PlayerManager.instance.next()
                R.id.ivPlay -> PlayerManager.instance.controlPlay()
                R.id.ivList -> playListFragment.show(mActivity.supportFragmentManager, "play_list")

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PlayBindingAdapter.anim = null
    }

}