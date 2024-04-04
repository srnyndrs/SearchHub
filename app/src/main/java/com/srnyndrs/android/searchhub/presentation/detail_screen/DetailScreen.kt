package com.srnyndrs.android.searchhub.presentation.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.srnyndrs.android.searchhub.domain.model.Owner
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.presentation.detail_screen.components.DetailsTopBar
import com.srnyndrs.android.searchhub.presentation.detail_screen.components.RepositoryDetail
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun DetailScreen(
    repository: Repository?,
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            DetailsTopBar(
                title = "Back to Home",
                onNavigationBack = onBack
            )
            Spacer(modifier = Modifier.padding(vertical = 3.dp))
            // Detail Content
            repository?.let {
                RepositoryDetail(repository = it)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DetailScreenPreview() {
    SearchHubTheme {
        Surface {
            DetailScreen(
                repository = Repository(
                    name = "k8s",
                    owner = Owner(
                        name = "srnyndrs",
                        profilePath = "",
                        avatarPath = ""
                    ),
                    description = "",
                    repositoryPath = "",
                    stars = 5,
                    forks = 5,
                    createdAt = "",
                    updatedAt = ""
                ),
                onBack = {}
            )
        }
    }
}