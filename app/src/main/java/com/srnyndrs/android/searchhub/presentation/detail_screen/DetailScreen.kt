package com.srnyndrs.android.searchhub.presentation.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.sample.SampleRepositoryProvider
import com.srnyndrs.android.searchhub.presentation.detail_screen.components.DetailsTopBar
import com.srnyndrs.android.searchhub.presentation.detail_screen.components.RepositoryDetail
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun DetailScreen(
    repository: Repository?,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            DetailsTopBar(
                title = "Back to Home",
                onNavigationBack = onBack
            )
            // Detail Content
            repository?.let {
                RepositoryDetail(repository = it)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DetailScreenPreview(
    @PreviewParameter(SampleRepositoryProvider::class) repositories: List<Repository>
) {
    SearchHubTheme {
        Surface {
            DetailScreen(
                repository = repositories[0],
                onBack = {}
            )
        }
    }
}