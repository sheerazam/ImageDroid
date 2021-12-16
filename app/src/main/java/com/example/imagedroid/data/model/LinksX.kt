package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    val followers: String? = null,
    val following: String? = null,
    val html: String? = null,
    val likes: String? = null,
    val photos: String? = null,
    val portfolio: String? = null,
    val self: String? = null
)