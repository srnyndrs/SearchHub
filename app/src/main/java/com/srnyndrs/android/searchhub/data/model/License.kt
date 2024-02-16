package com.srnyndrs.android.searchhub.data.model

import com.google.gson.annotations.SerializedName

data class License (
    val key: String? = null,
    val name: String? = null,

    @SerializedName("spdx_id")
    val spdxID: String? = null,

    val url: String? = null,

    @SerializedName("node_id")
    val nodeID: String? = null
)