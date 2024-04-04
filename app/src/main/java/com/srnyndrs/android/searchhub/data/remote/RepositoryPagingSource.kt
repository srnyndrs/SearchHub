package com.srnyndrs.android.searchhub.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.srnyndrs.android.searchhub.data.mapper.toDomain
import com.srnyndrs.android.searchhub.domain.model.Repository

class RepositoryPagingSource(
    private val apiService: ApiService,
    private val query: String
): PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getRepositories(
                query = query,
                page = position
            )

            if (response.items.isNotEmpty()) {
                LoadResult.Page(
                    data = response.items.map { it.toDomain() },
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position >= response.totalCount) null else (position + 1)
                )
            } else {
                LoadResult.Error(Exception("No Response"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}