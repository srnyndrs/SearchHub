package com.srnyndrs.android.searchhub.presentation.detail_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

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
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back button"
            )
        }
        // Navigation title
        Text(text = title)
    }
}

@PreviewLightDark
@Composable
fun TopBarPreview() {
    SearchHubTheme {
        Surface {
            DetailsTopBar(
                title = "Search results for \"docker\"",
                onNavigationBack = {}
            )
        }
    }
}