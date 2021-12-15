package com.example.imagedroid.data.base

class AppThrowable(val errorCode: Int? = 0, val errorMessage: String? = null, val errorTitle: String? = null) : Throwable(errorMessage)