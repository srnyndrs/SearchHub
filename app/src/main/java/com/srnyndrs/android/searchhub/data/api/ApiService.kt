package com.srnyndrs.android.searchhub.data.api

import com.srnyndrs.android.searchhub.data.model.SearchResponse
import com.srnyndrs.android.searchhub.data.shared.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // GET request for searching repositories
    @GET(Constants.REPOSITORY_SEARCH_PATH)
    suspend fun getRepositories(
        // Query parameter "q" for the search
        @Query("q") query: String
    ): SearchResponse
}