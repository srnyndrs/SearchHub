package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconLabel(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
    ) {
        Icon(imageVector = icon, contentDescription = icon.name)
        Spacer(modifier = Modifier.padding(2.dp))
        //Text(text = "${label}:")
        //Spacer(modifier = Modifier.padding(4.dp))
        Text(text = value)
    }
}