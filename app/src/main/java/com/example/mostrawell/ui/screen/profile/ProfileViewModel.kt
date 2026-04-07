package com.example.mostrawell.ui.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.ui.model.UserUiModel

class ProfileViewModel: ViewModel() {
    var user: UserUiModel? by mutableStateOf(UserUiModel("Alex", "19", "", EntertainmentTag.entries.toList()))
        private set
    var name: String by mutableStateOf("")
        private set
    var age: String by mutableStateOf("")
        private set
    var avatarUrl: String by mutableStateOf("")
        private set
    val tags: MutableList<Tag> = mutableStateListOf<Tag>()
}