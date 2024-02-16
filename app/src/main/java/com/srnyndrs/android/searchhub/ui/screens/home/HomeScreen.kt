package com.srnyndrs.android.searchhub.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.srnyndrs.android.searchhub.ui.composables.RepositoryList
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RepositoryViewModel = hiltViewModel()
) {
    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.repositories.value

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query.value,
                    onValueChange = {
                        query.value = it
                    },
                    enabled = true,
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    label = { Text(text = "Search") }
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Button(
                    onClick = {
                        viewModel.fetchRepositories(query.value)
                    }
                ) {
                    Text("Search")
                }
            }

            if (result.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            if (result.error.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = viewModel.repositories.value.error
                    )
                }
            }

            if (result.data.isNotEmpty()) {
                RepositoryList(list = viewModel.repositories.value.data, navController = navController)
            }
        }
    }
}