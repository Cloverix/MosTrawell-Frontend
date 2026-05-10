package com.example.mostrawell.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel(private val profileManager: ProfileManager, private val userRepository: UserRepository): ViewModel() {
    var nickname by mutableStateOf("")
        private set
    var age by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")      //Needs to be encoded instantly after user input
        private set
    var duplicatePassword by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<OperationResult>(OperationResult.Success)
    val uiState: StateFlow<OperationResult> = _uiState

    fun onNicknameChange(newNickname: String) {
        nickname = newNickname
    }

    fun onAgeChange(newAge: String) {
        age = newAge
    }

    fun onLoginChange(newLogin: String) {
        login = newLogin
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onDuplicatePasswordChange(newDuplicatePassword: String) {
        duplicatePassword = newDuplicatePassword
    }

    private fun validatePassword(): Boolean {
        return password.isNotBlank() && password == duplicatePassword
    }

    private suspend fun checkIfUserExists(): Boolean {
        val resource: Resource<UserUiModel> = userRepository.getByLogin(login)
        return resource is Resource.Success
    }

    suspend fun onDoneButtonClick(): OperationResult {
        _uiState.value = OperationResult.Loading
        try {
            if (checkIfUserExists()) {
                _uiState.value = OperationResult.Success
                return OperationResult.Failure("User with login $login already exists")
            }
            val userRegisterDto = UserRegisterDto(nickname, age.toInt(), login, password)
            val registeredUser: Resource<UserUiModel> = userRepository.register(userRegisterDto)
            return when (registeredUser) {
                is Resource.Success -> {
                    profileManager.saveProfile(registeredUser.data)
                    profileManager.savePassword(password)
                    _uiState.value = OperationResult.Success
                    OperationResult.Success
                }

                is Resource.Failure -> {
                    _uiState.value = OperationResult.Success
                    OperationResult.Failure(registeredUser.message)
                }
            }
        }
        catch (e: Exception) {
            _uiState.value = OperationResult.Success
            return OperationResult.Failure(e.message ?: "Unknown exception");
        }
    }

    fun isDoneButtonEnabled(): Boolean {
        return nickname.isNotBlank() && login.isNotBlank() && validatePassword()
    }
}