package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GetPhotosResponseItem(
    val alt_description: String? = null,
    val blur_hash: String? = null,
    val color: String? = null,
    val created_at: String? = null,
    val description: String? = null,
    val height: Int? = null,
    val id: String? = null,
    val liked_by_user: Boolean? = null,
    val likes: Int? = null,
    val links: Links? = null,
    val promoted_at: String? = null,
    val sponsorship: Sponsorship? = null,
    val topic_submissions: TopicSubmissions? = null,
    val updated_at: String? = null,
    val urls: Urls? = null,
    val user: User? = null,
    val width: Int? = null
)