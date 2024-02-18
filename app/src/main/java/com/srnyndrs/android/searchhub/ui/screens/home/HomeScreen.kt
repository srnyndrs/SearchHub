package com.srnyndrs.android.searchhub.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.srnyndrs.android.searchhub.ui.composables.QualifierSelector
import com.srnyndrs.android.searchhub.ui.composables.RepositoryList
import com.srnyndrs.android.searchhub.ui.composables.SearchBar
import com.srnyndrs.android.searchhub.ui.navigation.Screens
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RepositoryViewModel
) {
    // Variable to store search bar's textField value
    val query = remember { mutableStateOf(viewModel.query.value) }

    // Variable to store search bar's focus status
    val isSearchBarFocused = remember { mutableStateOf(false) }

    // Variable to store the query's result
    val result = viewModel.repositories.value

    // Variable to manage focus
    val focusManager = LocalFocusManager.current

    // Function to execute repository search
    val searchForRepositories = {
        viewModel.fetchRepositories(query.value)
        // Hide selection and clear search bar's focus
        isSearchBarFocused.value = false
        focusManager.clearFocus()
    }

    Surface(
        modifier = Modifier.fillMaxSize()
            // Make background clickable to be able to remove focus from the search bar
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { focusManager.clearFocus() }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 12.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Search Bar
            SearchBar(
                query = query,
                isSearchBarFocused = isSearchBarFocused,
                onSearch = { searchForRepositories() },
                onClear = { viewModel.clearRepositories() }
            )
            // Show qualifiers if the search bar is focused
            if(isSearchBarFocused.value) {
                QualifierSelector(query = query)
            }
            Spacer(modifier = Modifier.padding(6.dp))
            // Loading state handling
            if (result.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            // Error handling
            if (result.error.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .background(MaterialTheme.colorScheme.error)
                            .padding(12.dp),
                        text = viewModel.repositories.value.error,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
            // List repositories if the query was successful
            if (result.data.isNotEmpty()) {
                RepositoryList(
                    list = viewModel.repositories.value.data,
                    onNavigation = { index ->
                        navController.navigate("${Screens.DetailScreen.route}/${index}")
                    }
                )
            }
        }
    }
}