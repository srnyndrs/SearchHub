package com.srnyndrs.android.searchhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.srnyndrs.android.searchhub.presentation.navigation.NavigationGraph
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavigationGraph(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}