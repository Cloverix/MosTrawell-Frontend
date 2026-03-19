package com.example.mostrawell.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.log

class RegisterViewModel: ViewModel() {
    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")      //Needs to be encoded instantly after user input
        private set

    fun onLoginChange(newLogin: String) {
        login = newLogin
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun validatePassword(): Boolean {
        //TODO: add validation
        return true
    }

    fun onDoneButtonClick() {
        //TODO: POST login and password to DB
    }

    fun isDoneButtonEnabled(): Boolean {
        return login.isNotBlank() && validatePassword()
    }
}