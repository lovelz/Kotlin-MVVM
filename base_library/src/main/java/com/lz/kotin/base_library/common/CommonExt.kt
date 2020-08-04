package com.lz.kotin.base_library.common

import android.content.Context
import java.time.Duration
import java.util.*

/**
 * 通用方法
 * author: lovelz
 * date: on 2020-07-27
 */

/**
 * 集合是否为空
 */
fun isListEmpty(list: List<Any>?): Boolean = (list == null || list.isEmpty())

fun getRandom(start: Int, end: Int): Int {
    return ((start + Math.random() * (end - start + 1)).toInt())
}

/**
 * 将毫秒转换成分秒-00：00格式
 */
fun stringForTime(milliseconds: Int): String {
    val totalSeconds = milliseconds / 1000
    val seconds = totalSeconds % 60
    val minutes = (totalSeconds / 60) % 60

    return Formatter().format("%02d:%02d", minutes, seconds).toString()
}

/**
 * dp转px
 */
fun dp2px(context: Context, dpValue: Float): Int {
    val scale: Float = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * px转dp
 */
fun px2dp(context: Context, pxValue: Float): Int {
    val scale: Float = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}