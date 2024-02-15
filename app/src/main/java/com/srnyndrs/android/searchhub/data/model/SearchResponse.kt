package com.srnyndrs.android.searchhub.data.model

data class SearchResponse (
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<Repository>
)