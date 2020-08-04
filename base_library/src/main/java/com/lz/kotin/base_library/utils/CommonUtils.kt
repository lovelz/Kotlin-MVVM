package com.lz.kotin.base_library.utils

import androidx.core.content.ContextCompat
import com.lz.kotin.base_library.BaseApp

/**
 * 常用的方法
 *
 * author: lovelz
 * date: on 2020-07-22
 */
class CommonUtils {

    companion object {

        fun parseColor(color: Int): Int = ContextCompat.getColor(BaseApp.getContext(), color)

    }

}