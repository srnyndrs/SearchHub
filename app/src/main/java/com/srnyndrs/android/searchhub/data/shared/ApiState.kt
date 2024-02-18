package com.srnyndrs.android.searchhub.data.shared

import com.srnyndrs.android.searchhub.data.model.Repository

data class ApiState(
     val isLoading: Boolean = false,
     val data: List<Repository> = emptyList(),
     val error: String = ""
)