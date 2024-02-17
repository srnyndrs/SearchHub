package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.data.util.qualifiers

@Composable
fun QualifierSelector(
    selection: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground, RectangleShape)
            .padding(8.dp)
    ) {
        Text(text = "Qualifiers", fontSize = 14.sp)
        Divider(thickness = 1.dp)
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            // Qualifiers
            items(qualifiers) { qualifier ->
                Text(
                    text = qualifier,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .clickable {
                            selection.value = qualifier
                        },
                    fontSize = 20.sp
                )
            }
        }
        // Clear
        /*Text(text = "clear", modifier = Modifier.padding(top = 6.dp).clickable {
            selection.value = ""
        })*/
    }
}