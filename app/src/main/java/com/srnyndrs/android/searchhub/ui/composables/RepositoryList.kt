package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.ui.navigation.Screens

@Composable
fun RepositoryList(
    list: List<Repository>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        itemsIndexed(list) { index, repository ->
            RepositoryCard(
                repository = repository
            ) {
                navController.navigate("${Screens.DetailScreen.route}/${index}")
            }
        }
    }
}