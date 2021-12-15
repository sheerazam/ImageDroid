package com.example.imagedroid.di

import com.example.imagedroid.data.api.ApiClient
import com.example.imagedroid.data.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesImagesRepository(apiClient: ApiClient): ImagesRepository {
        return ImagesRepository(apiClient = apiClient)
    }

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return ApiClient()
    }
}