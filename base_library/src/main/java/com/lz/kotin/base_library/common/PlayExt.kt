package com.lz.kotin.base_library.common

import android.content.ContentUris
import android.net.Uri

/**
 * author: lovelz
 * date: on 2020-07-29
 */

fun albumById(albumId: Long): Uri {
    return ContentUris.withAppendedId(
        Uri.parse("content://media/external/audio/albumart"),
        albumId
    )
}