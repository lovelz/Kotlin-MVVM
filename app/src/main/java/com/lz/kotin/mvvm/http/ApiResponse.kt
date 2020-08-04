package com.lz.kotin.mvvm.http

import com.lz.kotin.base_library.http.ApiException
import java.io.Serializable

/**
 * author: lovelz
 * date: on 2020-08-04
 */
class ApiResponse<T> : Serializable {

    private var data: T? = null

    private var errorMsg = ""

    private var errorCode = 0

    fun data(): T {
        when (errorCode) {
            0, 200 -> {
                return data!!
            }
            -1001 -> {
                throw ApiException(errorMsg, errorCode)
            }
            -1 -> {
                throw ApiException(errorMsg, errorCode)
            }
        }
        throw ApiException(errorMsg, errorCode)
    }

}