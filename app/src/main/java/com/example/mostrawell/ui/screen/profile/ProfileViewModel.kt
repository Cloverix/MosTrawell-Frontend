package com.example.mostrawell.ui.screen.profile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.ProfileDataManager
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(profileManager: ProfileDataManager): ViewModel() {
    val user: StateFlow<UserUiModel?> = profileManager.getProfileFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
}