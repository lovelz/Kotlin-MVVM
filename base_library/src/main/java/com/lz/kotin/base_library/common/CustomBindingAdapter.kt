package com.lz.kotin.base_library.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * author: lovelz
 * date: on 2020-07-30
 */
object CustomBindingAdapter {

    @BindingAdapter(value = ["imgSrc"])
    @JvmStatic
    fun imgSrc(view: ImageView, id: Int) {
        view.setImageResource(id)
    }

    @BindingAdapter(value = ["imgUrl"])
    @JvmStatic
    fun imgUrl(view: ImageView, url: String) {
        view.loadUrl(view.context.applicationContext, url)
    }

    @BindingAdapter(value = ["imgUriCircle"])
    @JvmStatic
    fun imgUriCircle(view: ImageView, albumId: Long) {
        view.loadCircle(view.context.applicationContext, albumById(albumId))
    }

    @BindingAdapter(value = ["visible"])
    @JvmStatic
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}