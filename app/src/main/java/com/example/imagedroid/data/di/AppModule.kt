package com.example.imagedroid.data.di

import android.content.Context
import com.example.imagedroid.data.api.ApiClient
import com.example.imagedroid.data.repo.ImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiClient(
        @ApplicationContext context: Context
    ): ApiClient {
        return ApiClient(context = context)
    }

    @Provides
    @Singleton
    fun providesImagesRepository(
        apiClient : ApiClient
    ) : ImagesRepository{
        return ImagesRepository(apiClient = apiClient)
    }

}
