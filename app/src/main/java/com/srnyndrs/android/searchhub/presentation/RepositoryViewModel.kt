package com.srnyndrs.android.searchhub.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    private val _repositories: MutableStateFlow<PagingData<Repository>> = MutableStateFlow(value = PagingData.empty())
    val repositories = _repositories.asStateFlow()

    var selectedRepository: Repository? = null

    fun fetchRepositories(text: String) = viewModelScope.launch {
        repository.getRepositories(text)
            .cachedIn(this)
            .collect{
                _repositories.value = it
            }
    }

    fun clearRepositories() {
        _repositories.value = PagingData.empty()
    }
}