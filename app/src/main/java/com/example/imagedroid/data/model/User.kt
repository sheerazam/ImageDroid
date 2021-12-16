package com.example.imagedroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val accepted_tos: Boolean? = null,
    val bio: String? = null,
    val first_name: String? = null,
    val for_hire: Boolean? = null,
    val id: String? = null,
    val instagram_username: String? = null,
    val last_name: String? = null,
    val links: LinksXX? = null,
    val location: String? = null,
    val name: String? = null,
    val portfolio_url: String? = null,
    val profile_image: ProfileImageX? = null,
    val social: SocialX? = null,
    val total_collections: Int? = null,
    val total_likes: Int? = null,
    val total_photos: Int? = null,
    val twitter_username: String? = null,
    val updated_at: String? = null,
    val username: String? = null
)