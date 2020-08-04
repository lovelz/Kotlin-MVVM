package com.lz.kotin.base_library.base

/**
 * androidx 懒加载
 *
 * author: lovelz
 * date: on 2020-07-28
 */
abstract class LazyVmFragment : BaseVmFragment() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            isLoaded = true
            lazyInit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}