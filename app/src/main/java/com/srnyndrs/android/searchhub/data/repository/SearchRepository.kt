package com.srnyndrs.android.searchhub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.srnyndrs.android.searchhub.data.remote.ApiService
import com.srnyndrs.android.searchhub.data.remote.RepositoryPagingSource
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): SearchRepository {
    override fun getRepositories(query: String): Flow<PagingData<Repository>>{
        return Pager(
            config = PagingConfig(
                pageSize = ApiService.PAGE_SIZE,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { RepositoryPagingSource(apiService, query) }
        ).flow
    }
}