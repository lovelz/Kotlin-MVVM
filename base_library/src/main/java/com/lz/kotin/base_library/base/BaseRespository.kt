package com.lz.kotin.base_library.base

import androidx.lifecycle.MutableLiveData
import com.lz.kotin.base_library.http.ApiException
import com.lz.kotin.base_library.utils.toast
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

typealias Error = suspend (e: ApiException) -> Unit

/**
 * author: lovelz
 * date: on 2020-08-03
 */
open class BaseRespository(private val coroutineScope: CoroutineScope,
                           private val errorLiveData: MutableLiveData<ApiException>) {

    protected fun <T> launch (
        block: suspend () -> T,
        success: suspend (T) -> Unit,
        error: Error? = null
    ): Job {
        return coroutineScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                success(it)
            }.onFailure {
                it.printStackTrace()
                getApiException(it).apply {
                    error?.invoke(this)
                    toast(errorMessage)
                    errorLiveData.value = this
                }

            }
        }
    }

    private fun getApiException(e: Throwable): ApiException {
        return when (e) {
            is UnknownHostException -> {
                ApiException("网络异常", -100)
            }
            is JSONException -> {
                ApiException("数据异常", -100)
            }
            is SocketTimeoutException -> {
                ApiException("连接超时", -100)
            }
            is ConnectException -> {
                ApiException("连接错误", -100)
            }
            is HttpException -> {
                ApiException("http code ${e.code()}", -100)
            }
            is ApiException -> {
                e
            }
            is CancellationException -> {
                ApiException("", -10)
            }
            else -> {
                ApiException("未知错误", -100)
            }
        }
    }

}