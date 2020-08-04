package com.lz.kotin.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lz.kotin.base_library.base.BaseVmActivity
import com.lz.kotin.base_library.common.stringForTime
import com.lz.kotin.mvvm.play.AudioObserver
import com.lz.kotin.mvvm.play.PlayList
import com.lz.kotin.mvvm.play.PlayerManager
import com.lz.kotin.mvvm.play.bean.AudioBean
import com.lz.kotin.mvvm.ui.MainFragment

class MainActivity : BaseVmActivity(), AudioObserver {

    private var playVM: PlayViewModel? = null

    override fun initViewModel() {
        playVM = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun initEvent(savedInstanceState: Bundle?) {
        super.initEvent(savedInstanceState)
        PlayerManager.instance.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PlayerManager.instance.unregister(this)
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun onAudioBean(audioBean: AudioBean) {
        playVM?.songName?.set(audioBean.name)
        playVM?.singer?.set(audioBean.singer)
        playVM?.maxDuration?.set(stringForTime(audioBean.duration))
        playVM?.maxProgress?.set(audioBean.duration)
        playVM?.albumPic?.set(audioBean.albumId)
    }

    override fun onPlayStatus(playStatus: Int) {
        playVM?.playStatus?.set(playStatus)
    }

    override fun onProgress(currentDuration: Int, totalDuration: Int) {
        playVM?.currentDuration?.set(stringForTime(currentDuration))
        playVM?.playProgress?.set(currentDuration)
    }

    override fun onPlayMode(playMode: Int) {
        when(playMode) {
            PlayList.PlayMode.ORDER_PLAY_MODE -> playVM?.playModePic?.set(R.mipmap.play_order)
            PlayList.PlayMode.SINGLE_PLAY_MODE -> playVM?.playModePic?.set(R.mipmap.play_single)
            PlayList.PlayMode.RANDOM_PLAY_MODE -> playVM?.playModePic?.set(R.mipmap.play_random)
        }
    }

    override fun onBackPressed() {
        val mMainNavFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.host_fragment)
        val fragment = mMainNavFragment?.childFragmentManager?.primaryNavigationFragment
        if (fragment is MainFragment) {
            moveTaskToBack(false)
        } else {
            super.onBackPressed()
        }
    }

}
