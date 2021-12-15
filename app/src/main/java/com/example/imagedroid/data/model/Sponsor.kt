package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Sponsor(
    val accepted_tos: Boolean? = null,
    val bio: String? = null,
    val first_name: String? = null,
    val for_hire: Boolean? = null,
    val id: String? = null,
    val instagram_username: String? = null,
    val links: LinksX? = null,
    val name: String? = null,
    val portfolio_url: String? = null,
    val profile_image: ProfileImage? = null,
    val social: Social? = null,
    val total_collections: Int? = null,
    val total_likes: Int? = null,
    val total_photos: Int? = null,
    val twitter_username: String? = null,
    val updated_at: String? = null,
    val username: String? = null
)