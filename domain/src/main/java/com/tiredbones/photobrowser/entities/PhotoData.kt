package com.tiredbones.photobrowser.entities

data class PhotoData(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)
