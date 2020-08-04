package com.lz.kotin.base_library.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.lz.kotin.base_library.BaseApp

/**
 * toast扩展方法
 *
 * author: lovelz
 * date: on 2020-07-24
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    Toast.makeText(this, content, duration).apply {
        show()
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    BaseApp.getContext().toast(content, duration)
}

fun toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    BaseApp.getContext().toast(id, duration)
}