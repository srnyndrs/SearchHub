package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.shared.MockData

@Composable
fun RepositoryList(
    list: List<Repository>,
    onNavigation: (index: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        itemsIndexed(list) { index, repository ->
            RepositoryCard(
                repository = repository,
                onClick = { onNavigation(index) }
            )
        }
    }
}

@Preview
@Composable
fun RepositoryListPreview() {
    RepositoryList(
        list = listOf(MockData.repository, MockData.repository),
        onNavigation = {}
    )
}