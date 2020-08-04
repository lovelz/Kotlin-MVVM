package com.lz.kotin.mvvm.http

import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.lz.kotin.base_library.BaseApp.Companion.getContext
import com.lz.kotin.mvvm.constants.ApiConstants
import com.lz.kotin.mvvm.constants.Constants
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * author: lovelz
 * date: on 2020-08-03
 */
object RetrofitFactory {

    private val okHttpClientBuilder: OkHttpClient.Builder get() {
        return OkHttpClient.Builder()
            .readTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .connectTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .addInterceptor(getLogInterceptor())
            .cookieJar(getCookie())
            .cache(getCache())
    }

    fun factory(): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLogInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(getContext())
        )
    }

    private fun getCache(): Cache {
        return Cache(File(getContext().cacheDir, "cache"), 1024 * 1024 * 100)
    }

}