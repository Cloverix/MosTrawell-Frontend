package com.example.mostrawell.domain.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthState {
    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    fun getLoggedInState(): Boolean {
        return _isUserLoggedIn.value
    }

    fun setLoggedInState(loggedInState: Boolean) {
        _isUserLoggedIn.value = loggedInState
    }
}