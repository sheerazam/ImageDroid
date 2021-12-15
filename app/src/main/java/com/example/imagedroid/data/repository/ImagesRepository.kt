package com.example.imagedroid.data.repository

import com.example.imagedroid.data.api.ApiClient
import com.example.imagedroid.data.base.Result
import com.example.imagedroid.data.model.GetPhotosResponseItem
import io.ktor.client.request.*

class ImagesRepository(private val apiClient: ApiClient) {
    suspend fun loadPhotos(): Result<List<GetPhotosResponseItem>> {
        val map = apiClient.client.get<List<GetPhotosResponseItem>> {
            url("https://api.unsplash.com/photos")
            parameter("page", "1")
            parameter("per_page", "10")
            parameter("order_by", "popular")
        }
        return Result.Success(map)
    }
}