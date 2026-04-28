package com.example.mostrawell.ui.screen.edit_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileDataManager
import com.example.mostrawell.ui.model.UserUiModel

class EditProfileViewModel(profileManager: ProfileDataManager, userRepository: UserRepository): ViewModel() {
    //TODO: для тестов имя задано изначально; позже его нужно будет получать из DataStore (вошедший в данный момент пользователь)
    var name: String by mutableStateOf("Alex")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onDoneButtonClick() {
        if (name.isNotBlank()) {
            //TODO: Save changed name to database & navigate to ProfileScreen
        }
    }
}