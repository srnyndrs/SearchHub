package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.data.shared.Constants

@Composable
fun QualifierSelector(
    // The state variable to change according to selection
    query: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground, RectangleShape)
            .padding(8.dp)
    ) {
        Text(
            text = "Qualifiers",
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            // List qualifiers
            items(Constants.qualifiers) { qualifier ->
                Text(
                    text = qualifier,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .clickable {
                            query.value += qualifier
                        },
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun QualifierSelectorPreview() {
    QualifierSelector(
        query = remember { mutableStateOf("") }
    )
}