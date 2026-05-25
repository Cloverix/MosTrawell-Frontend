package com.example.mostrawell.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val profileManager: ProfileManager,
    private val userRepository: UserRepository,
    private val authState: AuthState
): ViewModel() {
    val user: StateFlow<UserUiModel?> = profileManager.getProfileFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _uiState = MutableStateFlow<OperationResult>(OperationResult.Loading)
    val uiState: StateFlow<OperationResult> = _uiState

    private val _showDeleteAccountDialog = MutableStateFlow(false)
    val showDeleteAccountDialog: StateFlow<Boolean> = _showDeleteAccountDialog

    private var _showLogoutDialog = MutableStateFlow(false)
    val showLogoutDialog: StateFlow<Boolean> = _showLogoutDialog

    init {
        viewModelScope.launch {
            user.collect { userData ->
                _uiState.value = if (userData != null) OperationResult.Success else OperationResult.Loading
            }
        }
    }

    fun toggleDeleteDialog() {
        _showDeleteAccountDialog.value = !_showDeleteAccountDialog.value
    }

    fun toggleLogoutDialog() {
        _showLogoutDialog.value = !_showLogoutDialog.value
    }

    fun logout() {
        authState.setLoggedInState(false)
    }

    fun deleteUser() {
        _uiState.value = OperationResult.Loading
        viewModelScope.launch {
            when (val response = userRepository.deleteUser()) {
                is Resource.Success -> {
                    _uiState.value = OperationResult.Success
                    authState.setLoggedInState(false)
                }
                is Resource.Failure -> {
                    _uiState.value = OperationResult.Failure("Error: ${response.message}")
                }
            }
        }
    }
}