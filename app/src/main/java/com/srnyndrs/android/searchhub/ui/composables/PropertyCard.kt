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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun PropertyCard (
    icon: ImageVector,
    label: String,
    value: String,
    secondValue: String = ""
) {
    Card (
        modifier = Modifier
            .width(156.dp)
            .height(156.dp)
            .padding(4.dp),
        /*colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
        )*/
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = icon, contentDescription = icon.name)
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                Text(text = label)
            }
            Spacer(modifier = Modifier.padding(6.dp))
            if(secondValue.isEmpty()) {
                Text(text = value)
            } else {
                Text(text = value)
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Text(text = secondValue)
            }
        }
    }
}