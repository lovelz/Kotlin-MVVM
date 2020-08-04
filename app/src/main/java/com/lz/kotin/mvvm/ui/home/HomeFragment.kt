package com.lz.kotin.mvvm.ui.home

import android.os.Bundle
import com.lz.kotin.base_library.base.BaseVmFragment
import com.lz.kotin.base_library.base.DataBindingConfig
import com.lz.kotin.mvvm.R

/**
 * author: lovelz
 * date: on 2020-07-29
 */
class HomeFragment : BaseVmFragment() {
    override fun initEvent(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home)
    }
}