package com.example.mostrawell.ui.screen.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignInViewModel: ViewModel() {
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

    fun onDoneButtonClick() {
        //TODO: POST login and password to DB
    }

    fun onSignUpButtonClick() {
        //navigate to Register screen
    }

    fun isDoneButtonEnabled(): Boolean {
        return login.isNotBlank() && password.isNotBlank()
    }
}