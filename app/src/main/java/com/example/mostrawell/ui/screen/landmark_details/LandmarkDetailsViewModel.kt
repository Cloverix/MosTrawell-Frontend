package com.example.mostrawell.ui.screen.landmark_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrawell.domain.repository.LandmarkRepository
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.LandmarkUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LandmarkDetailsViewModel(
    private val landmarkId: Long,
    private val userRepository: UserRepository,
    private val landmarkRepository: LandmarkRepository,
    private val profileManager: ProfileManager
): ViewModel() {
    private var _uiState = MutableStateFlow<OperationResult>(OperationResult.Loading)
    val uiState: StateFlow<OperationResult> = _uiState

    private var _landmark = MutableStateFlow<LandmarkUiModel?>(null)
    val landmark: StateFlow<LandmarkUiModel?> = _landmark

    private var _isFavourite = MutableStateFlow(false)
    val isFavourite: StateFlow<Boolean> = _isFavourite

    private var toggleFavouriteJob: Job? = null

    init {
        viewModelScope.launch {
            val userFavouritesId = profileManager.getProfile()?.favouriteLandmarksId ?: emptySet()
            when (val loadedLandmark = landmarkRepository.getById(landmarkId)) {
                is Resource.Success -> {
                    _landmark.value = loadedLandmark.data
                    _isFavourite.value = loadedLandmark.data.id in userFavouritesId
                    _uiState.value = OperationResult.Success
                }
                is Resource.Failure -> {
                    _uiState.value = OperationResult.Failure(loadedLandmark.message)
                }
            }
        }
    }

    fun onFavouritePressed() {
        toggleFavouriteJob?.cancel()
        toggleFavouriteJob = viewModelScope.launch {
            _isFavourite.value = !_isFavourite.value
            delay(500)
            val userId = profileManager.getId() ?: return@launch
            val landmarkId = _landmark.value?.id ?: return@launch
            toggleFavouriteStatus(userId, landmarkId)
        }
    }

    private suspend fun toggleFavouriteStatus(userId: Long, landmarkId: Long) {
        val favouriteLandmarksId = (profileManager.getFavourites() ?: emptyList()).toMutableList()
        if (_isFavourite.value) {
            val response = userRepository.addFavouriteLandmark(userId, landmarkId)
            if (response is Resource.Success) {
                favouriteLandmarksId.add(landmarkId)
                profileManager.updateFavourites(favouriteLandmarksId)
            }
        }
        else {
            val response = userRepository.removeFavouriteLandmark(userId, landmarkId)
            if (response is Resource.Success) {
                favouriteLandmarksId.remove(landmarkId)
                profileManager.updateFavourites(favouriteLandmarksId)
            }
        }
    }
}