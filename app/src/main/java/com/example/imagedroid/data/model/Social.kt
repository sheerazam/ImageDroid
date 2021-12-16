package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Social(
    val instagram_username: String? = null,
    val portfolio_url: String? = null,
    val twitter_username: String? = null
)