package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Health(
    val approved_on: String? = null,
    val status: String? = null
)