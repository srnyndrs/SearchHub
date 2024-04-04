package com.srnyndrs.android.searchhub.presentation.detail_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun PropertyCard (
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    values: List<String>
) {
    Card (
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Label with Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = icon, contentDescription = icon.name)
                Spacer(modifier = Modifier.padding(horizontal = 1.dp))
                Text(text = label, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.padding(vertical = 3.dp))
            // Values
            for(text in values) {
                Text(text = text, modifier = Modifier.padding(vertical = 3.dp), fontSize = 16.sp)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PropertyCardPreview() {
    SearchHubTheme {
        Surface {
            PropertyCard(
                modifier = Modifier.size(128.dp),
                icon = Icons.Default.Star,
                label = "Stars",
                values = listOf("25")
            )
        }
    }
}