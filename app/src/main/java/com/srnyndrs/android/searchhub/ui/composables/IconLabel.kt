package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconLabel(
    icon: ImageVector,
    value: String
) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = icon.name)
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text(text = value, fontSize = 16.sp)
    }
}

@Preview
@Composable
fun IconLabelPreview(){
    IconLabel(icon = Icons.Default.Star, value = "25")
}