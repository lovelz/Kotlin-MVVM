package com.lz.kotin.mvvm.ui

import android.animation.ValueAnimator
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.lz.kotin.base_library.common.AnimUtil
import com.lz.kotin.base_library.common.loadBlurTrans
import com.lz.kotin.mvvm.play.PlayerManager
import com.lz.kotin.base_library.common.albumById
import com.lz.kotin.mvvm.view.FloatPlayLayout

/**
 * author: lovelz
 * date: on 2020-07-29
 */
object PlayBindingAdapter {

    /**
     * 加载图片，高斯模糊处理
     */
    @BindingAdapter(value = ["imgPlayBlur"])
    @JvmStatic
    fun imgPlayBlur(view: ImageView, albumId: Long) {
        view.loadBlurTrans(view.context.applicationContext,
            albumById(albumId), 90)
    }

    /**
     * 处理暂停播放
     */
    @BindingAdapter(value = ["imgPlay"])
    @JvmStatic
    fun imgPlay(view: ImageView, playStatus: Int) {
        when(playStatus) {
            PlayerManager.RELEASE, PlayerManager.PAUSE -> {
                view.isSelected = false
            }
            PlayerManager.START, PlayerManager.RESUME -> {
                view.isSelected = true
            }
        }
    }

    /**
     * 悬浮 处理暂停播放
     */
    @BindingAdapter(value = ["floatImgPlay"])
    @JvmStatic
    fun floatImgPlay(view: FloatPlayLayout, playStatus: Int?) {
        when(playStatus) {
            PlayerManager.RELEASE, PlayerManager.PAUSE -> {
                view.isSelected = false
            }
            PlayerManager.START, PlayerManager.RESUME -> {
                view.isSelected = true
            }
        }
    }

    /**
     * 悬浮 歌名
     */
    @BindingAdapter(value = ["floatSongName"])
    @JvmStatic
    fun floatSongName(view: FloatPlayLayout, songName: String?) {
        view.setSongName(songName)
    }

    /**
     * 悬浮 图片
     */
    @BindingAdapter(value = ["floatImgAlbum"])
    @JvmStatic
    fun floatImgAlbum(view: FloatPlayLayout, albumId: Long?) {
        view.setAlbumPic(albumId)
    }

    /**
     * 旋转动画
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @BindingAdapter(value = ["rotate"])
    @JvmStatic
    fun rotate(view: View, playStatus: Int) {
        if (anim == null) {
            anim = AnimUtil.getRepeatRotate(view, 20000)
        }
        when(playStatus) {
            PlayerManager.RELEASE -> {

            }
            PlayerManager.START, PlayerManager.RESUME -> {
                if (anim?.isPaused!!) {
                    anim?.resume()
                } else {
                    anim?.start()
                }
            }
            PlayerManager.PAUSE -> {
                anim?.pause()
            }
        }
    }

    /**
     * 图片动画
     */
    var anim: ValueAnimator? = null

}