package com.srnyndrs.android.searchhub.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("total_count")
    val totalCount: Long,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    val items: List<Repository>
)