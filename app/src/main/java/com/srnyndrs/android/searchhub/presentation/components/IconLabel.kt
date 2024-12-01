package com.srnyndrs.android.searchhub.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun IconLabel(
    icon: ImageVector,
    text: String? = null,
    value: String
) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = icon,
            contentDescription = icon.name,
        )
        Text(
            text = value,
            fontSize = 14.sp
        )
        text?.let {
            Text(
                text = it,
                fontSize = 14.sp
            )
        }
    }
}

@PreviewLightDark
@Composable
fun IconLabelPreview() {
    SearchHubTheme {
        Surface {
            IconLabel(
                icon = Icons.Default.Star,
                text = "stars",
                value = "25"
            )
        }
    }
}