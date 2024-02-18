package com.srnyndrs.android.searchhub.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.srnyndrs.android.searchhub.ui.composables.RepositoryDetail
import com.srnyndrs.android.searchhub.ui.composables.DetailsTopBar
import com.srnyndrs.android.searchhub.ui.navigation.Screens
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: RepositoryViewModel,
    repositoryIndex: Int
) {
    // Variable to store the selected repository
    val repository = viewModel.getRepository(repositoryIndex)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            DetailsTopBar(
                title = "Results for \"${viewModel.query.value}\"",
                onNavigationBack = {
                    // Navigate back to Home screen
                    navController.navigate(Screens.HomeScreen.route){
                        popUpTo(0)
                    }
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 3.dp))
            // Detail Content
            RepositoryDetail(repository = repository)
        }
    }
}