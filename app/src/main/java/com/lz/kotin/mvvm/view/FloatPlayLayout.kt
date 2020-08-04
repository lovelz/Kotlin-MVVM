package com.lz.kotin.mvvm.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.lz.kotin.base_library.common.dp2px
import com.lz.kotin.base_library.common.loadCircle
import com.lz.kotin.base_library.utils.clickNoRepeat
import com.lz.kotin.mvvm.R
import com.lz.kotin.base_library.common.albumById
import kotlinx.android.synthetic.main.play_float_layout.view.*

/**
 * 首页音频播放悬浮窗
 *
 * author: lovelz
 * date: on 2020-07-28
 */
class FloatPlayLayout : LinearLayout {

    private var isOpen = false

    private val contentWidth = dp2px(context, 180f)

    constructor(context: Context?) : super(context) {
        initView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    private fun initView(context: Context?) {
        View.inflate(context, R.layout.play_float_layout, this)
        gravity = Gravity.CENTER
        initEvent()
    }

    private fun initEvent() {
        ivMusicPic.clickNoRepeat {
            if (!isOpen) {
                startAnim()
                ivMusicPic.isEnabled = false
                isOpen = true
            }
        }

        ivShrink.clickNoRepeat {
            if (isOpen) {
                startAnim()
                ivMusicPic.isEnabled = true
                isOpen = false
            }
        }
    }

    /**
     * 开启动画
     */
    private fun startAnim() {
        val animator = if (isOpen) {
            ValueAnimator.ofInt(contentWidth, 0)
        } else {
            ValueAnimator.ofInt(0, contentWidth)
        }
        animator.duration = 249
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            val params = llContent.layoutParams as MarginLayoutParams
            params.width = value
            llContent.layoutParams = params
        }
        animator.start()
    }

    fun playClick(onClick: (View) -> Unit) {
        ivPlaying.clickNoRepeat {
            onClick.invoke(it)
        }
    }

    fun rootClick(onClick: (View) -> Unit) {
        root.clickNoRepeat {
            onClick.invoke(it)
        }
    }

    fun setSongName(songName: String?) {
        songName?.apply {
            tvSongName.text = this
        }
    }

    fun setAlbumPic(albumId: Long?) {
        albumId?.apply {
            ivMusicPic.loadCircle(context.applicationContext,
                albumById(albumId)
            )
        }
    }

}