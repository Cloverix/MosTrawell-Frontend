package com.example.mostrawell.domain.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.mostrawell.R
import com.example.mostrawell.data.userDataStore
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ProfileDataManager(private val context: Context) {
    companion object {
        val USER_ID = longPreferencesKey("user_id")
        val USER_LOGIN = stringPreferencesKey("user_login")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_AGE = intPreferencesKey("user_age")
        val USER_AVATAR_URL = stringPreferencesKey("user_avatar_url")
        val USER_TAGS = stringSetPreferencesKey("user_tags")
    }

    fun getProfileFlow(): Flow<UserUiModel?> {
        return context.userDataStore.data.map { preferences ->
            val id = preferences[USER_ID] ?: return@map null
            val login = preferences[USER_LOGIN] ?: return@map null
            val name = preferences[USER_NAME] ?: return@map null
            val age = (preferences[USER_AGE] ?: return@map null).toString()
            val avatarUrl = preferences[USER_AVATAR_URL] ?: ""
            val tags = (preferences[USER_TAGS] ?: emptySet()).mapNotNull { findTagByName(it) }.toSet()
            UserUiModel(id, login, name, age, avatarUrl, tags)
        }
    }

    suspend fun getProfile(): UserUiModel? {
        return getProfileFlow().first()
    }

    suspend fun saveProfile(user: UserUiModel) {
        if (validateAge(user.age) != null) {
            context.userDataStore.edit { preferences ->
                preferences[USER_ID] = user.id
                preferences[USER_LOGIN] = user.login
                preferences[USER_NAME] = user.name
                preferences[USER_AGE] = user.age.toInt()
                preferences[USER_AVATAR_URL] = user.avatarUrl ?: ""
                preferences[USER_TAGS] = user.tags.map { tag -> tag.getName() }.toSet()
            }
        }
    }

    suspend fun updateName(name: String) {
        context.userDataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }

    suspend fun updateTags(tags: Set<Tag>) {
        context.userDataStore.edit { preferences ->
            preferences[USER_TAGS] = tags.map { tag -> tag.getName() }.toSet()
        }
    }
}