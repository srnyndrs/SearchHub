package com.srnyndrs.android.searchhub.data.mapper

import com.srnyndrs.android.searchhub.data.remote.dto.Owner
import com.srnyndrs.android.searchhub.data.remote.dto.Repository

fun Repository.toDomain(): com.srnyndrs.android.searchhub.domain.model.Repository {
    return com.srnyndrs.android.searchhub.domain.model.Repository(
        name = this.name,
        owner = this.owner.toDomain(),
        description = this.description ?: "No description provided.",
        repositoryPath = this.htmlURL ?: "",
        stars = this.stargazersCount ?: 0L,
        forks = this.forksCount ?: 0L,
        createdAt = this.createdAt ?: "Unknown",
        updatedAt = this.updatedAt ?: "Unknown"
    )
}

fun Owner.toDomain(): com.srnyndrs.android.searchhub.domain.model.Owner {
    return com.srnyndrs.android.searchhub.domain.model.Owner(
        name = this.login,
        avatarPath = this.avatarURL,
        profilePath = this.htmlURL
    )
}