package com.example.mostrawell.ui.screen.interest_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.AuthState
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InterestSelectionViewModel(
    private val profileManager: ProfileManager,
    private val userRepository: UserRepository,
    private val authState: AuthState): ViewModel() {
    var user: StateFlow<UserUiModel?> = profileManager.getProfileFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _selectedTags = MutableStateFlow<Set<Tag>>(emptySet())
    val selectedTags: StateFlow<Set<Tag>> = _selectedTags

    private val _uiState = MutableStateFlow<OperationResult>(OperationResult.Success)
    val uiState: StateFlow<OperationResult> = _uiState

    init {
        viewModelScope.launch {
            user.collect { data ->
                _selectedTags.value = data?.tags ?: emptySet<Tag>()
            }
        }
    }

    fun addSelectedTag(tag: Tag) {
        _selectedTags.update { it + tag }
    }

    fun removeSelectedTag(tag: Tag) {
        _selectedTags.update { it - tag }
    }

    fun onDoneButtonClick() {
        val userId = user.value?.id ?: return
        viewModelScope.launch {
            _uiState.value = OperationResult.Loading
            profileManager.updateTags(_selectedTags.value)
            val resource = userRepository.changeTags(userId, _selectedTags.value.map { it.originalName }.toSet())
            if (resource is Resource.Success) {
                //authState.setLoggedInState(true)
                _uiState.value = OperationResult.Success
            }
            else {
                _uiState.value = OperationResult.Failure((resource as Resource.Failure).message)
            }
        }
    }
}