package com.example.mostrawell.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.ui.model.UserUiModel
import com.example.mostrawell.ui.navigation.Route
import kotlin.math.log

class RegisterViewModel: ViewModel() {
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

    fun onNicknameChange(newNickname: String) {
        nickname = newNickname
    }

    fun onAgeChange(newAge: String) {
        age = newAge
    }

    fun validateAge(age: String): String? {       //Return age as a string if valid, else return null
        try {
            val ageNum = age.toInt()
            if (ageNum in 12..100) {        //Неясная причина отказа, если возраст выходит за допустимые рамки
                return age
            }
            return null
        }
        catch (e: NumberFormatException) {
            return null
        }
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
        //TODO: add validation and check that password and duplicatePassword are the same
        return true
    }

    fun onDoneButtonClick(navController: NavHostController) {
        //TODO: POST login and password to DB
        // or return "User with such login and password already exists" exception
        navController.navigate(Route.InterestSelection.route)
    }

    fun onSignInButtonClick(navController: NavHostController) {
        navController.navigate(Route.SignIn.route)
    }

    fun isDoneButtonEnabled(): Boolean {
        return nickname.isNotBlank() && login.isNotBlank() && validatePassword()
    }
}