package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PropertyCard (
    icon: ImageVector,
    label: String,
    values: List<String>
) {
    Card (
        modifier = Modifier
            .width(156.dp)
            .height(128.dp)
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

@Preview
@Composable
fun PropertyCardPreview() {
    PropertyCard(
        icon = Icons.Default.Star,
        label = "Stars",
        values = listOf("25")
    )
}