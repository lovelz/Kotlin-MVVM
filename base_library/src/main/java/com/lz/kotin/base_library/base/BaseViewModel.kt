package com.lz.kotin.base_library.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lz.kotin.base_library.http.ApiException

/**
 * author: lovelz
 * date: on 2020-07-27
 */
open class BaseViewModel : ViewModel() {

    /**
     * 错误信息
     */
    val errorLiveData = MutableLiveData<ApiException>()

    /**
     * 空数据
     */
    val emptyLiveData = MutableLiveData<Any>()

}