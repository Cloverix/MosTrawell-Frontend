package com.example.mostrawell.data.remote

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mostrawell.domain.util.ProfileManager
import kotlinx.coroutines.flow.first

class AuthManager(
    private val profileManager: ProfileManager
) {
    suspend fun getCredentials(): Pair<String, String>? {
        val login = profileManager.getLogin() ?: return null
        val password = profileManager.getPassword() ?: return null
        return login to password
    }
}