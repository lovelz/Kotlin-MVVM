package com.lz.kotin.mvvm.http

/**
 * author: lovelz
 * date: on 2020-08-03
 */
class RetrofitManager {

    companion object {
        private val map = mutableMapOf<Class<*>, Any>()

        private val retrofit = RetrofitFactory.factory()

        fun <T : Any> getApiService(apiClass: Class<T>): T {
            return getApiService(apiClass)
        }

        private fun <T : Any> getService(apiClass: Class<T>): T {
            return if (map[apiClass] == null) {
                synchronized(RetrofitManager::class.java) {
                    val t = retrofit.create(apiClass)
                    if (map[apiClass] == null) {
                        map[apiClass] = t
                    }
                    t
                }
            } else {
                map[apiClass] as T
            }
        }
    }

}