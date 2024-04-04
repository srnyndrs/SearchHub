package com.srnyndrs.android.searchhub.domain.repository

import androidx.paging.PagingData
import com.srnyndrs.android.searchhub.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getRepositories(query: String): Flow<PagingData<Repository>>
}