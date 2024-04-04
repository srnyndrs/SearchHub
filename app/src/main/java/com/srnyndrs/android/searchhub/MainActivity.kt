package com.srnyndrs.android.searchhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.srnyndrs.android.searchhub.presentation.RepositoryViewModel
import com.srnyndrs.android.searchhub.presentation.NavigationGraph
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<RepositoryViewModel>()
            SearchHubTheme {
                Surface {
                    NavigationGraph(
                        viewModel = viewModel,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}