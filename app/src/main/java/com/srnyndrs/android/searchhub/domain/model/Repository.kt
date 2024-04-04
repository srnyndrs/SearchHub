package com.srnyndrs.android.searchhub.domain.model

data class Repository(
    val name: String,
    val owner: Owner,
    val description: String,
    val repositoryPath: String,
    val stars: Long,
    val forks: Long,
    val createdAt: String,
    val updatedAt: String
)