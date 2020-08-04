package com.lz.kotin.base_library.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * view视图扩展方法
 * author: lovelz
 * date: on 2020-07-23
 */

/**
 * 注册多个view
 * 防止重复点击
 */
fun setNoRepeatClick(vararg views: View, interval: Long = 400, onClick: (View) -> Unit) {
    views.forEach {
        it.clickNoRepeat(interval = interval) { view ->
            onClick.invoke(view)
        }
    }
}

/**
 * 防止重复点击
 */
var lastTime = 0L
fun View.clickNoRepeat(interval: Long = 400, onClick: (View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastTime != 0L && (currentTime - lastTime) < interval) {
            return@setOnClickListener
        }
        lastTime = currentTime
        onClick(it)
    }
}

fun ViewPager2.initFragment(fragment: Fragment, fragments: MutableList<Fragment>): ViewPager2 {
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment = fragments[position]
        override fun getItemCount(): Int = fragments.size
    }
    return this
}