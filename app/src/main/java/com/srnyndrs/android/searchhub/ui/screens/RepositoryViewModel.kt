package com.srnyndrs.android.searchhub.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.repository.SearchRepository
import com.srnyndrs.android.searchhub.data.shared.ApiState
import com.srnyndrs.android.searchhub.data.shared.Resource
import com.srnyndrs.android.searchhub.data.util.decodeError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {
    // Variable to store the state of the query
    val repositories: MutableState<ApiState> = mutableStateOf(ApiState())

    // Variable to store search history
    val query = mutableStateOf("")

    fun fetchRepositories(text: String) = viewModelScope.launch {
        // Set search history
        query.value = text
        // Notify UI about loading
        repositories.value = ApiState(isLoading = true)
        try {
            when(val result = repository.getRepositories(text)) {
                is Resource.Error -> {
                    // Set error message according to the API error response
                    repositories.value = ApiState(error = result.message.decodeError())
                }
                is Resource.Success -> {
                    val data = result.data?.items ?: listOf()
                    if(data.isNotEmpty()) {
                        // Set the results
                        repositories.value = ApiState(data = data)
                    } else {
                        // Set error message if there are no results
                        repositories.value = ApiState(error = "No results found")
                    }
                }
            }
        } catch (e: Exception) {
            // Set error message if there is an exception
            repositories.value = ApiState(error = "Failed to fetch data")
        }
    }

    // Returns with the repository in the given position
    fun getRepository(index: Int): Repository {
        return repositories.value.data[index]
    }

    // Clears the query
    fun clearRepositories() {
        repositories.value = ApiState()
        query.value = ""
    }
}