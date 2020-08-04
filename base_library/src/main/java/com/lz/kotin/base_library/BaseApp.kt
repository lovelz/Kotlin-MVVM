package com.lz.kotin.base_library

import android.app.Application
import android.content.Context

/**
 * author: lovelz
 * date: on 2020-07-22
 */
open class BaseApp : Application() {

    companion object {

        //lateinit 延迟初始化
        private lateinit var baseApplication: BaseApp

        fun getContext(): Context {
            return  baseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
    }

}