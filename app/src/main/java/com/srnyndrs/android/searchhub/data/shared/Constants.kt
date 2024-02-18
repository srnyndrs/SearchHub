package com.srnyndrs.android.searchhub.data.shared

object Constants {
    const val BASE_URL = "https://api.github.com/"
    const val REPOSITORY_SEARCH_PATH = "search/repositories"
    const val DESCRIPTION_CHAR_LIMIT = 35
    val qualifiers = arrayListOf(
        "user:",
        "stars:",
        "forks:",
        "created:",
        "pushed:",
        "in:name:",
        "in:description:"
    )
    val errorCodes = hashMapOf(
        "HTTP 304" to "Not modified" ,
        "HTTP 422" to "Validation failed, or the endpoint has been spammed.",
        "HTTP 503" to "Service unavailable"
    )
}