package com.example.bellavoice.model

data class SongBean @JvmOverloads constructor(
    val id: Long = 0L,
    val uri: String = "",
    val song: String = "", //歌名
    val singer: String = "", //歌手名称
    val duration: String = "", //时常
    val path: String = ""
)
