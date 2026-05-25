package com.example.mostrawell.ui.screen.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EditProfileViewModel(private val profileManager: ProfileManager, private val userRepository: UserRepository): ViewModel() {
    val user: StateFlow<UserUiModel?> = profileManager.getProfileFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    init {
        viewModelScope.launch {
            user.collect { userData ->
                _name.value = userData?.name ?: ""
            }
        }
    }

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onFocusLost() {
        if (_name.value.isBlank()) {
            _name.value = user.value?.name ?: ""
        }
    }

    fun onDoneButtonClick() {
        val userId = user.value?.id ?: return
        viewModelScope.launch {
            if (_name.value.isNotBlank()) {
                profileManager.updateName(_name.value)
                userRepository.changeName(userId, _name.value)
            }
        }
    }
}