package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val download: String? = null,
    val download_location: String? = null,
    val html: String? = null,
    val self: String? = null
)