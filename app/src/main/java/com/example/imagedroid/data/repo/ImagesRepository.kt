package com.example.imagedroid.data.repo

import com.example.imagedroid.data.api.ApiClient
import com.example.imagedroid.data.model.GetPhotosResponseItem
import com.example.imagedroid.data.utils.Result
import io.ktor.client.request.*

class ImagesRepository(private val apiClient: ApiClient) {
    suspend fun loadPhotos(): Result<List<GetPhotosResponseItem>> {
        val list = apiClient.client.get<List<GetPhotosResponseItem>> {
            url("https://api.unsplash.com/photos")
            parameter("page", "1")
            parameter("per_page", "10")
            parameter("order_by", "popular")
        }
        return Result.Success(list)
    }
}