package com.example.mostrawell.ui.screen.sign_in

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.domain.util.OperationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(
    private val userRepository: UserRepository,
    private val profileManager: ProfileManager
): ViewModel() {
    //TODO: после тестирования убрать изначальный ввод логина пароля
    var login by mutableStateOf("login")
        private set
    var password by mutableStateOf("qwerty")      //TODO: Needs to be encoded instantly after user input
        private set

    private var _unmaskPasswordField = MutableStateFlow(false)
    val unmaskPasswordField: StateFlow<Boolean> = _unmaskPasswordField

    private var _uiState = MutableStateFlow<OperationResult>(OperationResult.Success)
    val uiState: StateFlow<OperationResult> = _uiState

    fun onLoginChange(newLogin: String) {
        login = newLogin
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    suspend fun onDoneButtonClick(): OperationResult {
        _uiState.value = OperationResult.Loading
        val validationResult = userRepository.login(login, password)
        if (validationResult is Resource.Success) {
            profileManager.saveProfile(validationResult.data)
            profileManager.savePassword(password)
            _uiState.value = OperationResult.Success
            return OperationResult.Success
        }
        else {
            _uiState.value = OperationResult.Failure((validationResult as Resource.Failure).message)
            return OperationResult.Failure("Wrong login or password. Try again")
        }
    }

    fun isDoneButtonEnabled(): Boolean {
        return login.isNotBlank() && password.isNotBlank()
    }

    fun onUnmaskPasswordField() {
        _unmaskPasswordField.value = !_unmaskPasswordField.value
    }
}