package com.lz.kotin.base_library.base

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 * author: lovelz
 * date: on 2020-07-27
 */
class DataBindingConfig constructor(val layout: Int, val stateViewModel: ViewModel?) {

    constructor(layout: Int) : this(layout, null)

    private val bindingParams = SparseArray<Any>()

    fun getBindingParams(): SparseArray<Any> {
        return bindingParams
    }

    fun addBindingParam(variableId: Int, any: ViewModel?): DataBindingConfig {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, any)
        }
        return this
    }

}