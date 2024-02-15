package com.srnyndrs.android.searchhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.srnyndrs.android.searchhub.ui.navigation.NavigationGraph
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RepositoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchHubTheme {
                NavigationGraph(
                    viewModel = viewModel,
                    navController = rememberNavController()
                )
            }
        }
    }
}