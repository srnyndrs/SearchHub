package com.srnyndrs.android.searchhub.presentation.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.srnyndrs.android.searchhub.domain.model.Owner
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.presentation.home_screen.components.QualifierSelector
import com.srnyndrs.android.searchhub.presentation.home_screen.components.RepositoryCard
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    items: LazyPagingItems<Repository>,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onNavigation: (Repository?) -> Unit
) {
    // Variable to store search bar's textField value
    val query = rememberSaveable { mutableStateOf("") }

    // Variable to store search bar's focus status
    val isSearchBarFocused = remember { mutableStateOf(false) }

    // Variable to manage focus
    val focusManager = LocalFocusManager.current

    val listState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = Modifier
            .fillMaxSize()
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
            /*Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchBar(
                    query = query,
                    isSearchBarFocused = isSearchBarFocused,
                    onSearch = {
                        onSearch(query.value)
                    },
                    onClear = onClear
                )
                // Show qualifiers if the search bar is focused
                if (isSearchBarFocused.value) {
                    //QualifierSelector(query = query)
                }
            }*/
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = query.value,
                onQueryChange = {
                    query.value = it
                },
                onSearch = {
                    onSearch(query.value)
                    isSearchBarFocused.value = false
                },
                active = isSearchBarFocused.value,
                onActiveChange = {
                    isSearchBarFocused.value = it
                },
                placeholder = {
                    Text(text = "Search repositories")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    // Clear button
                    if(query.value.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                query.value = ""
                                onClear()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Button"
                            )
                        }
                    }
                },
            ) {
                QualifierSelector(query = query)
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { focusManager.clearFocus() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when(items.loadState.refresh) {
                    is LoadState.Loading -> {
                        //CircularProgressIndicator()
                    }

                    is LoadState.Error -> {
                        val errorState = items.loadState.refresh as LoadState.Error
                        Text(text = errorState.error.message.toString())
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            state = listState
                        ) {
                            items(items.itemCount) { index ->
                                val item = items[index]
                                RepositoryCard(
                                    repository = item,
                                    onClick = {
                                        onNavigation(item)
                                    }
                                )
                            }
                            if(items.loadState.append is LoadState.Loading) {
                                item {
                                    LinearProgressIndicator()
                                }
                            } else if(items.loadState.append is LoadState.Error) {
                                item {
                                    Text(
                                        modifier = Modifier.padding(vertical = 6.dp),
                                        text = "Failed to load more results",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        // TODO()
        // Loading state handling
        /*if (result.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }*/
        // Error handling
        /*if (result.error.isNotBlank()) {
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
        }*/
    }
}

@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    SearchHubTheme {
        Surface {
            HomeScreen(
                items = flowOf(
                    PagingData.from(
                        data = listOf(
                            Repository(
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
                            )
                        ),
                        sourceLoadStates =
                        LoadStates(
                            refresh = LoadState.NotLoading(false),
                            append = LoadState.NotLoading(false),
                            prepend = LoadState.NotLoading(false),
                        ),
                    ),
                ).collectAsLazyPagingItems(),
                onSearch = {},
                onClear = {},
                onNavigation = {}
            )
        }
    }
}