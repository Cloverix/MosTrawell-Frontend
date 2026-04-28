package com.example.mostrawell.ui.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.ProfileDataManager
import com.example.mostrawell.ui.model.UserUiModel

class ProfileViewModel(profileManager: ProfileDataManager): ViewModel() {
    //TODO: переделать для работы с ProfileDataManager
    var user: UserUiModel? by mutableStateOf(UserUiModel(1, "Alex", "19", null, EntertainmentTag.entries.toSet()))
        private set
    //TODO: Все, что ниже, можно убрать, если user непосредственно на экране ProfileScreen не изменяется
    var name: String by mutableStateOf("")
        private set
    var age: String by mutableStateOf("")
        private set
    var avatarUrl: String by mutableStateOf("")
        private set
    val tags: MutableList<Tag> = mutableStateListOf<Tag>()

    fun onEditTagsButtonClick(navController: NavHostController) {
        /*TODO: navigate to interest selection screen*/
    }

    fun onEditProfileButtonClick(navController: NavHostController) {
        /*TODO: navigate to profile editing screen*/
    }
}