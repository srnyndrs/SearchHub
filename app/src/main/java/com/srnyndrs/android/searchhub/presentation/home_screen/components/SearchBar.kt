package com.srnyndrs.android.searchhub.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun SearchBar(
    query: MutableState<String>,
    isSearchBarFocused: MutableState<Boolean>,
    onSearch: () -> Unit,
    onClear: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.7f)
                .onFocusChanged {
                    isSearchBarFocused.value = it.isFocused
                },
            value = TextFieldValue(
                text = query.value,
                selection = TextRange(query.value.length)
            ),
            onValueChange = { textFieldValue ->
                query.value = textFieldValue.text
            },
            enabled = true,
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                // Clear button
                if(query.value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            query.value = ""
                            onClear()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Button"
                        )
                    }
                }
            },
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if(query.value.isNotEmpty()) {
                        onSearch()
                    }
                }
            ),
        )
        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
        // Search button
        OutlinedButton(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .padding(top = 12.dp, bottom = 6.dp)
                .weight(0.3f),
            onClick = onSearch,
            shape = RoundedCornerShape(4.dp),
            enabled = query.value.isNotEmpty()
        ) {
            Text(text = "Search")
        }
    }
}

@PreviewLightDark
@Composable
fun SearchBarPreview() {
    SearchHubTheme {
        Surface {
            SearchBar(
                query = remember {mutableStateOf("") },
                isSearchBarFocused = remember {mutableStateOf(false) },
                onSearch = {},
                onClear = {}
            )
        }
    }
}