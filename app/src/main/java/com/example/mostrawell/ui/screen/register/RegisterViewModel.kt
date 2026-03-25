package com.example.mostrawell.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.log

class RegisterViewModel: ViewModel() {
    var nickname by mutableStateOf("")
        private set
    var age by mutableStateOf(0)
        private set

    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")      //Needs to be encoded instantly after user input
        private set
    var duplicatePassword by mutableStateOf("")
        private set

    fun onNicknameChange(newNickname: String) {
        nickname = newNickname
    }

    fun onAgeChange(newAge: Int) {
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

    fun validatePassword(): Boolean {
        //TODO: add validation and check that password and duplicatePassword are the same
        return true
    }

    fun onDoneButtonClick() {
        //TODO: POST login and password to DB
    }

    fun onSignInButtonClick() {
        //navigate to Sign in screen
    }

    fun isDoneButtonEnabled(): Boolean {
        return login.isNotBlank() && validatePassword()
    }
}