package com.example.imagedroid.data.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class ApiClient {
    val client = HttpClient(OkHttp) {


        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
            header("Accept", "application/json")
            header("Authorization", "Client-ID wxl8gDYQvKHvTcfTzfFJ2Fy0GoSuKoJovMopdieYBvk")
        }

        engine {

        }
    }

}

val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}