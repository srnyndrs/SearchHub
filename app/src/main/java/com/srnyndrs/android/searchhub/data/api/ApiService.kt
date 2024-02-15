package com.srnyndrs.android.searchhub.data.api

import com.srnyndrs.android.searchhub.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") text: String
    ): SearchResponse
}