package com.srnyndrs.android.searchhub.data.repository

import com.srnyndrs.android.searchhub.data.api.ApiService
import com.srnyndrs.android.searchhub.data.model.SearchResponse
import com.srnyndrs.android.searchhub.data.util.Resource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRepositories(text: String): Resource<SearchResponse> {
        return try {
            val result = apiService.getRepositories(text)
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}