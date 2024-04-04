package com.srnyndrs.android.searchhub.data.mapper

fun com.srnyndrs.android.searchhub.data.remote.Repository.toDomain(): com.srnyndrs.android.searchhub.domain.model.Repository {
    return com.srnyndrs.android.searchhub.domain.model.Repository(
        name = name,
        owner = owner.toDomain(),
        description = this.description ?: "",
        repositoryPath = this.htmlURL ?: "",
        stars = this.stargazersCount ?: 0L,
        forks = forksCount ?: 0L,
        createdAt = createdAt ?: "",
        updatedAt = pushedAt ?: ""
    )
}

fun com.srnyndrs.android.searchhub.data.remote.Owner.toDomain(): com.srnyndrs.android.searchhub.domain.model.Owner {
    return com.srnyndrs.android.searchhub.domain.model.Owner(
        name = this.login,
        avatarPath = avatarURL,
        profilePath = htmlURL
    )
}