package com.srnyndrs.android.searchhub.data.remote

import com.srnyndrs.android.searchhub.data.remote.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val PAGE_SIZE = 20
    }

    @GET("search/repositories?per_page=$PAGE_SIZE")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int
    ): SearchResponse
}