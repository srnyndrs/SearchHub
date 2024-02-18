package com.srnyndrs.android.searchhub.data.shared

import com.srnyndrs.android.searchhub.data.model.Owner
import com.srnyndrs.android.searchhub.data.model.Repository

object MockData {
    val owner = Owner(
        login = "srnyndrs",
        htmlURL = "",
        avatarURL = "https://avatars.githubusercontent.com/u/113044246?v=4"
    )
    val repository = Repository(
        name = "docker-learn",
        description = "Repository to learn docker",
        stargazersCount = 5,
        forksCount = 5,
        createdAt = "2024-02-18T10:00:00Z",
        updatedAt = "2024-02-18T12:00:00Z",
        owner = owner,
        htmlURL = "",
    )
}