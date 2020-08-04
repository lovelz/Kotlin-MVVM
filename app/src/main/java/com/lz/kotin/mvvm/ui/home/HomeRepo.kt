package com.lz.kotin.mvvm.ui.home

import androidx.lifecycle.MutableLiveData
import com.lz.kotin.base_library.base.BaseRespository
import com.lz.kotin.base_library.http.ApiException
import com.zs.wanandroid.entity.BannerBean
import com.zs.zs_jetpack.bean.ArticleBean
import kotlinx.coroutines.CoroutineScope

/**
 * author: lovelz
 * date: on 2020-08-03
 */
class HomeRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRespository(coroutineScope, errorLiveData) {

    private var page = 0

    fun getArticleList(
        isRefresh: Boolean,
        articleLiveData: MutableLiveData<MutableList<ArticleBean.DatasBean>>,
        banner: MutableLiveData<MutableList<BannerBean>>
    ) {
        if (isRefresh) {
            page = 0

        }
    }

    private fun getTopList(articleLiveData: MutableLiveData<MutableList<ArticleBean.DatasBean>>) {
        launch(
            block = {

            },
            success = {

            }
        )
    }

}