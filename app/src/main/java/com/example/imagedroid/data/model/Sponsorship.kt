package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Sponsorship(
    val impression_urls: List<String>? = null,
    val sponsor: Sponsor? = null,
    val tagline: String? = null,
    val tagline_url: String? = null
)