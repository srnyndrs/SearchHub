package com.srnyndrs.android.searchhub.presentation.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.sample.SampleRepositoryProvider
import com.srnyndrs.android.searchhub.domain.util.decodeError
import com.srnyndrs.android.searchhub.presentation.home_screen.components.CustomSearchBar
import com.srnyndrs.android.searchhub.presentation.home_screen.components.RepositoryCard
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    items: LazyPagingItems<Repository>,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onNavigation: (Repository) -> Unit
) {

    val query = rememberSaveable { mutableStateOf("") }
    val searchInProgress = remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val listState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { focusManager.clearFocus() }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CustomSearchBar(
                modifier = Modifier.fillMaxWidth(),
                search = query.value,
                onValueChange = {
                    query.value = it
                },
                onSearch = { value ->
                    searchInProgress.value = true
                    onSearch(value)
                    focusManager.clearFocus()
                },
                onClear = {
                    query.value = ""
                    onClear()
                }
            )
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
                        if (searchInProgress.value) {
                            CircularProgressIndicator()
                        }
                    }

                    is LoadState.Error -> {
                        searchInProgress.value = false
                        val errorState = items.loadState.refresh as LoadState.Error

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorState.error.message.decodeError(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    else -> {
                        searchInProgress.value = false
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            state = listState
                        ) {
                            items(items.itemCount) { index ->
                                val item = items[index]
                                item?.let { repository ->
                                    RepositoryCard(
                                        repository = repository,
                                        onClick = {
                                            onNavigation(repository)
                                        }
                                    )
                                }
                            }
                            if(items.loadState.append is LoadState.Loading) {
                                item {
                                    LinearProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun HomeScreenPreview(
    @PreviewParameter(SampleRepositoryProvider::class) repositories: List<Repository>
) {
    SearchHubTheme {
        Surface {
            HomeScreen(
                items = flowOf(
                    PagingData.from(
                        data = repositories,
                        sourceLoadStates = LoadStates(
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