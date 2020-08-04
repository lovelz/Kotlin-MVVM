package com.lz.kotin.mvvm.play.bean

/**
 * 歌曲文件信息
 *
 * author: lovelz
 * date: on 2020-07-24
 */
class AudioBean {

    var name: String? = null

    var singer: String? = null

    var size: Long = 0

    var duration = 0

    var path: String? = null

    var albumId: Long = 0

    var id: Long = 0

    override fun toString(): String {
        return "AudioBean(name=$name, singer=$singer, size=$size, duration=$duration, path=$path, albumId=$albumId, id=$id)"
    }


}