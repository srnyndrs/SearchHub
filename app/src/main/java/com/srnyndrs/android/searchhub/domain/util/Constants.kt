package com.srnyndrs.android.searchhub.domain.util

object Constants {
    const val DESCRIPTION_CHAR_LIMIT = 50
    val errorCodes = hashMapOf(
        "HTTP 304" to "Not modified" ,
        "HTTP 422" to "Validation failed, or the endpoint has been spammed",
        "HTTP 503" to "Service unavailable"
    )
}