package com.lz.kotin.base_library.common

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * author: lovelz
 * date: on 2020-07-29
 */
object AnimUtil {

    /**
     * 一直旋转的动画
     */
    fun getRepeatRotate(view: View, duration: Int): ValueAnimator {
        val anim = ValueAnimator.ofFloat(0f, 360f)
        anim.duration = duration.toLong()
        anim.repeatCount = 100000
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener {
            view.rotation = it.animatedValue as Float
        }
        return anim
    }

}