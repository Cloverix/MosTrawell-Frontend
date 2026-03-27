package com.example.mostrawell.ui.screen.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mostrawell.ui.navigation.Route

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
        //TODO: validate user
    }

    fun onSignUpButtonClick(navController: NavHostController) {
        navController.navigate(Route.Register.route)
    }

    fun isDoneButtonEnabled(): Boolean {
        return login.isNotBlank() && password.isNotBlank()
    }
}