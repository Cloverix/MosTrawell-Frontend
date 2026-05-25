package com.example.mostrawell.ui.screen.recommendation_feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrawell.domain.repository.LandmarkRepository
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.LandmarkUiModel
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecommendationFeedViewModel(
    private val landmarkRepository: LandmarkRepository,
    private val profileManager: ProfileManager
): ViewModel() {
    private val user: StateFlow<UserUiModel?> = profileManager.getProfileFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _uiState = MutableStateFlow<OperationResult>(OperationResult.Loading)
    val uiState: StateFlow<OperationResult> = _uiState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private var _foundLandmarks = MutableStateFlow<List<LandmarkUiModel>>(emptyList())
    val foundLandmarks: StateFlow<List<LandmarkUiModel>> = _foundLandmarks

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            user.collect { userData ->
                _uiState.value = OperationResult.Loading
                when (val searchResults = landmarkRepository.getByTags(userData?.tags ?: emptySet())) {
                    is Resource.Success -> {
                        _foundLandmarks.value = searchResults.data
                        _uiState.value = OperationResult.Success
                    }

                    is Resource.Failure -> {
                        _foundLandmarks.value = emptyList()
                        _uiState.value = OperationResult.Failure("Couldn't load any landmarks")
                    }
                }
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            onSearch(newQuery)
        }
    }

    fun onSearchImmediate(query: String) {
        viewModelScope.launch {
            onSearch(query)
        }
    }

    fun clearInputField() {
        _query.value = ""
    }

    private suspend fun onSearch(query: String) {
        _uiState.value = OperationResult.Loading
        when (val searchResults = if (query.isNotEmpty()) landmarkRepository.getByName(query)
        else landmarkRepository.getByTags(user.value?.tags ?: emptySet())) {
            is Resource.Success -> {
                _foundLandmarks.value = searchResults.data
                _uiState.value = OperationResult.Success
            }
            is Resource.Failure -> {
                _uiState.value = OperationResult.Failure("Landmarks not found")
            }
        }
    }
}