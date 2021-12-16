package com.example.imagedroid.data.utils

sealed class Result<out T : Any?> {

    data class Success<out T : Any?>(val data: T) : Result<T>()
    data class Error(val throwable: AppThrowable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
        }
    }

    fun <T : Any> failure(): Result<T> {
        return when (this) {
            is Error -> {
                Error(throwable)
            }
            else -> Error(throwable = AppThrowable(errorMessage = ""))
        }

    }
}

class AppThrowable(val errorCode: Int? = 0, val errorMessage: String? = null, val errorTitle: String? = null) : Throwable(errorMessage)