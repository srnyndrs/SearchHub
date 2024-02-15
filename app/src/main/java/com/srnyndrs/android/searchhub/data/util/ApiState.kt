package com.srnyndrs.android.searchhub.data.util

import com.srnyndrs.android.searchhub.data.model.Repository

data class ApiState(
     val isLoading: Boolean = false,
     val data: List<Repository> = emptyList(),
     val error: String = ""
)