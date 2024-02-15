package com.srnyndrs.android.searchhub.data.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Error<T>(message: String) : Resource<T>(message = message)
    class Success<T>(data: T?) : Resource<T>(data = data)
}