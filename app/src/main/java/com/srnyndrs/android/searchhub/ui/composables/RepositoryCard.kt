package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.ui.screens.detail.DetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCard(repository: Repository, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
        onClick = onClick
    ) {
        Text(text = repository.name ?: "Unknown")
    }
}