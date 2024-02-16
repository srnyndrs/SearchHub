package com.srnyndrs.android.searchhub.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.srnyndrs.android.searchhub.ui.composables.RepositoryDetail
import com.srnyndrs.android.searchhub.ui.navigation.Screens
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: RepositoryViewModel,
    repositoryIndex: Int
) {
    val repository = viewModel.getRepository(repositoryIndex)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        ) {
            // Upper bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(Screens.HomeScreen.route){
                            popUpTo(0)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = "Results for \"${viewModel.selectedQualifier.value}${viewModel.query.value}\"")
                Spacer(modifier = Modifier.fillMaxWidth())
            }
            // Content
            RepositoryDetail(repository = repository)
        }
    }
}