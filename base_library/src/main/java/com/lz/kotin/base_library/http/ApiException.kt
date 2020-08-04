package com.lz.kotin.base_library.http

/**
 * author: lovelz
 * date: on 2020-07-27
 */
class ApiException(val errorMessage: String, val errorCode: Int) : Throwable()