package com.srnyndrs.android.searchhub.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.repository.SearchRepository
import com.srnyndrs.android.searchhub.data.util.ApiState
import com.srnyndrs.android.searchhub.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {
    val repositories: MutableState<ApiState> = mutableStateOf(ApiState())

    fun fetchRepositories(text: String) = viewModelScope.launch {
        repositories.value = ApiState(isLoading = true)
        try {
            when(val result = repository.getRepositories(text)){
                is Resource.Error -> {
                    repositories.value = ApiState(error = result.message.toString())
                }
                is Resource.Success -> {
                    result.data?.items?.let {
                        repositories.value = ApiState(data = it)
                    }
                }
            }
        } catch (e: Exception) {
            repositories.value = ApiState(error = "Failed to fetch data")
        }
    }

    fun getRepository(index: Int): Repository {
        return repositories.value.data[index]
    }
}