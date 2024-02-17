package com.srnyndrs.android.searchhub.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.ui.composables.QualifierSelector
import com.srnyndrs.android.searchhub.ui.composables.RepositoryList
import com.srnyndrs.android.searchhub.ui.screens.RepositoryViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RepositoryViewModel
) {
    val query = remember { mutableStateOf(viewModel.query.value) }
    val result = viewModel.repositories.value
    val selectedQualifier = remember { mutableStateOf(viewModel.selectedQualifier.value) }
    val qualifierSelection = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val searchForRepository = {
        qualifierSelection.value = false
        focusManager.clearFocus()
        viewModel.fetchRepositories(selectedQualifier.value, query.value)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                {
                    focusManager.clearFocus()
                },
            verticalArrangement = Arrangement.Top
        ) {
            // Search Bar
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
                            qualifierSelection.value = it.isFocused
                        },
                    value = TextFieldValue(
                        text = selectedQualifier.value + query.value,
                        selection = TextRange(selectedQualifier.value.length + query.value.length)
                    ),
                    onValueChange = { textFieldValue ->
                        if(selectedQualifier.value.isNotEmpty()) {
                            if(textFieldValue.text.length < selectedQualifier.value.length) {
                                query.value = ""
                                selectedQualifier.value = ""
                            } else {
                                query.value = textFieldValue.text.split(":", limit = 2)[1]
                            }
                        } else {
                            query.value = textFieldValue.text
                        }
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
                        if(query.value.isNotEmpty() || selectedQualifier.value.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    query.value = ""
                                    selectedQualifier.value = ""
                                    viewModel.clearRepositories()
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
                                searchForRepository()
                            }
                        }
                    ),
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                        .padding(top = 12.dp, bottom = 6.dp)
                        .weight(0.3f),
                    onClick = { searchForRepository() },
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.surface,
                        disabledContainerColor = MaterialTheme.colorScheme.outline,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                    shape = RoundedCornerShape(4.dp),
                    enabled = query.value.isNotEmpty()
                ) {
                    Text(text = "Search")
                }
            }

            if(qualifierSelection.value) {
                QualifierSelector(selection = selectedQualifier)
            }

            Spacer(modifier = Modifier.padding(6.dp))

            if (result.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            if (result.error.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .padding(horizontal = 12.dp)
                            .background(MaterialTheme.colorScheme.error)
                            .padding(12.dp),
                        text = viewModel.repositories.value.error,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }

            if (result.data.isNotEmpty()) {
                RepositoryList(list = viewModel.repositories.value.data, navController = navController)
            }
        }
    }
}