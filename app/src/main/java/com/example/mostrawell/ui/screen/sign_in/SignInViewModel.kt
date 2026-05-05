package com.example.mostrawell.ui.screen.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileDataManager
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.domain.util.OperationResult

class SignInViewModel(private val userRepository: UserRepository, private val profileManager: ProfileDataManager): ViewModel() {
    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")      //TODO: Needs to be encoded instantly after user input
        private set

    fun onLoginChange(newLogin: String) {
        login = newLogin
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    suspend fun onDoneButtonClick(): OperationResult {
        val validationResult = userRepository.login(login, password)
        if (validationResult is Resource.Success) {
            profileManager.saveProfile(validationResult.data)
            return OperationResult.Success
        }
        else {
            return OperationResult.Failure("Wrong login or password. Try again")
        }
    }

    fun isDoneButtonEnabled(): Boolean {
        return login.isNotBlank() && password.isNotBlank()
    }
}