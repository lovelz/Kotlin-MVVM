package com.lz.kotin.base_library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ktx.immersionBar

/**
 * mvvm Activity 基类
 * author: lovelz
 * date: on 2020-07-22
 */
abstract class BaseVmActivity : AppCompatActivity() {

    private var mActivityProvider: ViewModelProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let { setContentView(it) }
        setStatusColor()
        initViewModel()
        initObserve()
        initEvent(savedInstanceState)
    }

    open fun setStatusColor() {
        immersionBar {
//            statusBarColor(CommonUtils.parseColor(R.color.transparent))
            statusBarDarkFont(true, 0.2f)

        }
    }

    /**
     * 初始化ViewModel
     */
    open fun initViewModel() {

    }

    /**
     * 注册LiveData观察者
     */
    open fun initObserve() {

    }

    /**
     * 初始化相关
     */
    open fun initEvent(savedInstanceState: Bundle?) {

    }

    /**
     * 布局
     */
    abstract fun getLayoutId(): Int?

    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(modelClass)
    }

}