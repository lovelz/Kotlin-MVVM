package com.lz.kotin.mvvm.play

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.lz.kotin.mvvm.play.bean.AudioBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * author: lovelz
 * date: on 2020-07-27
 */

/**
 * 读取音频文件列表
 */
fun readPlayList(context: Context, audioList: MutableList<AudioBean>) {
    GlobalScope.launch {
        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val audioBean = AudioBean()
                audioBean.name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                audioBean.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                audioBean.singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                audioBean.path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audioBean.duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                audioBean.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audioBean.albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                if (audioBean.duration > 60000) {
                    audioList.add(audioBean)
                }
            }
            cursor.close()
            Log.i("PlayList","$audioList")

        }
    }
}