package com.lz.kotin.base_library.common

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * 图片加载扩展方法
 *
 * author: lovelz
 * date: on 2020-07-29
 */

fun ImageView.loadUrl(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

/**
 * 高斯模糊+渐入渐出
 */
fun ImageView.loadBlurTrans(context: Context, uri: Uri, radius: Int) {
    Glide.with(context)
        .load(uri)
        .thumbnail(0.1f).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .apply(RequestOptions.bitmapTransform(CenterBlurTransformation(radius, 8, context)))
        .transition(DrawableTransitionOptions.withCrossFade(400))
        .into(this)
}

/**
 * 圆形图片
 */
fun ImageView.loadCircle(context: Context, uri: Uri) {
    Glide.with(context)
        .load(uri)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}