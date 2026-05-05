package com.example.mostrawell.ui.screen.landmark_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrawell.domain.repository.LandmarkRepository
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.LandmarkUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LandmarkDetailsViewModel(
    private val landmarkId: Long,
    private val landmarkRepository: LandmarkRepository
): ViewModel() {
    private var _uiState = MutableStateFlow<OperationResult>(OperationResult.Loading)
    val uiState: StateFlow<OperationResult> = _uiState

    private var _landmark = MutableStateFlow<LandmarkUiModel?>(null)
    val landmark: StateFlow<LandmarkUiModel?> = _landmark

    init {
        viewModelScope.launch {
            when (val loadedLandmark = landmarkRepository.getById(landmarkId)) {
                is Resource.Success -> {
                    _landmark.value = loadedLandmark.data
                    _uiState.value = OperationResult.Success
                }
                is Resource.Failure -> {
                    _uiState.value = OperationResult.Failure(loadedLandmark.message)
                }
            }
        }
    }


}