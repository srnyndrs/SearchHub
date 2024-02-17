package com.srnyndrs.android.searchhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.srnyndrs.android.searchhub.ui.navigation.NavigationGraph
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchHubTheme {
                Column(
                    modifier = Modifier.padding(top = 3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*Row(
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        Image(painterResource(R.mipmap.ic_launcher_round), contentDescription = null, modifier = Modifier.size(32.dp))
                        Text(text = "Search Hub")
                    }
                    Spacer(modifier = Modifier.padding(vertical = 3.dp))*/
                    NavigationGraph(
                        viewModel = hiltViewModel(),
                        navController = rememberNavController()
                    )
                }

            }
        }
    }
}