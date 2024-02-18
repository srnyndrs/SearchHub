package com.srnyndrs.android.searchhub.data.repository

import com.srnyndrs.android.searchhub.data.api.ApiService
import com.srnyndrs.android.searchhub.data.model.SearchResponse
import com.srnyndrs.android.searchhub.data.shared.Resource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    // Inject ApiService dependency into the repository
    private val apiService: ApiService
) {
    // Suspend function to fetch repositories based on the provided string
    suspend fun getRepositories(query: String): Resource<SearchResponse> {
        return try {
            val result = apiService.getRepositories(query)
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}