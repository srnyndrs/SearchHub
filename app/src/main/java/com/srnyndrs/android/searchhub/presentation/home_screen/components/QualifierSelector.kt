package com.srnyndrs.android.searchhub.presentation.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.domain.util.Constants
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun QualifierSelector(
    // The state variable to change according to selection
    query: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .padding(8.dp)
    ) {
        Text(
            text = "Qualifiers",
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
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

@PreviewLightDark
@Composable
fun QualifierSelectorPreview() {
    SearchHubTheme {
        Surface {
            QualifierSelector(
                query = remember { mutableStateOf("") }
            )
        }
    }
}