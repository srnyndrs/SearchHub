package com.srnyndrs.android.searchhub.domain.model

data class ApiState(
    val isLoading: Boolean = false,
    val data: List<Repository> = emptyList(),
    val error: String = ""
)