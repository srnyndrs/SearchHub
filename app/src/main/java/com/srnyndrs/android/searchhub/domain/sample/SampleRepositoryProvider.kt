package com.srnyndrs.android.searchhub.domain.sample

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.srnyndrs.android.searchhub.domain.model.Owner
import com.srnyndrs.android.searchhub.domain.model.Repository

class SampleRepositoryProvider: PreviewParameterProvider<List<Repository>> {
    override val values = sequenceOf(
        listOf(
            Repository(
                name = "k8s",
                owner = Owner(
                    name = "srnyndrs",
                    profilePath = "",
                    avatarPath = ""
                ),
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                repositoryPath = "",
                stars = 5,
                forks = 5,
                createdAt = "2024-11-02T16:00:44Z",
                updatedAt = "2024-11-22T13:20:44Z"
            ),
            Repository(
                name = "docker",
                owner = Owner(
                    name = "srnyndrs",
                    profilePath = "",
                    avatarPath = ""
                ),
                description = "No description provided",
                repositoryPath = "",
                stars = 15,
                forks = 20,
                createdAt = "2024-10-02T11:05:44Z",
                updatedAt = "2024-10-26T21:48:44Z"
            )
        )
    )
}