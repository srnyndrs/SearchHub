package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailsTopBar(
    title: String,
    onNavigationBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        IconButton(
            onClick = onNavigationBack
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        // Navigation title
        Text(text = title)
    }
}

@Preview
@Composable
fun TopBarPreview() {
    DetailsTopBar(
        title = "Search results for \"docker\"",
        onNavigationBack = {}
    )
}