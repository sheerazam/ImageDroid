package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileImageX(
    val large: String? = null,
    val medium: String? = null,
    val small: String? = null
)