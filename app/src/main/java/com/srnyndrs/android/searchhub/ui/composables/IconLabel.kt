package com.srnyndrs.android.searchhub.ui.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconLabel(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row (
        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = icon.name)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = "${label}:")
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = value)
    }
}