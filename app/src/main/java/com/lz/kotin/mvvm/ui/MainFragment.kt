package com.lz.kotin.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.lz.kotin.base_library.base.BaseVmFragment
import com.lz.kotin.base_library.base.DataBindingConfig
import com.lz.kotin.base_library.utils.initFragment
import com.lz.kotin.mvvm.BR
import com.lz.kotin.mvvm.PlayViewModel
import com.lz.kotin.mvvm.R
import com.lz.kotin.mvvm.play.PlayerManager
import com.lz.kotin.mvvm.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * author: lovelz
 * date: on 2020-07-28
 */
class MainFragment : BaseVmFragment() {

    private var playViewModel: PlayViewModel? = null

    private val fragmentList = arrayListOf<Fragment>()

    private val homeFragment by lazy { HomeFragment() }
    private val homeFragment1 by lazy { HomeFragment() }
    private val homeFragment2 by lazy { HomeFragment() }
    private val homeFragment3 by lazy { HomeFragment() }
    private val homeFragment4 by lazy { HomeFragment() }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(homeFragment1)
            add(homeFragment2)
            add(homeFragment3)
            add(homeFragment4)
        }
    }

    override fun initViewModel() {
        playViewModel = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun initEvent(savedInstanceState: Bundle?) {
        vpHome.initFragment(this, fragmentList).run {
            offscreenPageLimit = fragmentList.size
        }
        vpHome.isUserInputEnabled = false
        vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnNav.menu.getItem(position).isChecked = true
            }
        })
        btnNav.run {
            setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.menu_home -> vpHome.setCurrentItem(0, false)
                    R.id.menu_project -> vpHome.setCurrentItem(1, false)
                    R.id.menu_square -> vpHome.setCurrentItem(2, false)
                    R.id.menu_official_account -> vpHome.setCurrentItem(3, false)
                    R.id.menu_mine -> vpHome.setCurrentItem(4, false)
                }
                true
            }
        }
    }

    override fun onClick() {
        floatLayout.playClick {
            PlayerManager.instance.controlPlay()
        }
        floatLayout.rootClick {
            nav().navigate(R.id.action_main_fragment_to_play_fragment)
        }
    }

    override fun getLayoutId() = R.layout.fragment_main

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_main)
            .addBindingParam(BR.vm, playViewModel)
    }
}