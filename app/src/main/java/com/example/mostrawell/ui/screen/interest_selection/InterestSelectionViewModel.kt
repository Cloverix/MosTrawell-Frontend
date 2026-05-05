package com.example.mostrawell.ui.screen.interest_selection

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileDataManager
import com.example.mostrawell.ui.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InterestSelectionViewModel(private val profileManager: ProfileDataManager, private val userRepository: UserRepository): ViewModel() {
    //TODO: переделать под работу с ProfileDataManager и UserRepo
    var user: StateFlow<UserUiModel?> = profileManager.getProfileFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _selectedTags = MutableStateFlow<Set<Tag>>(emptySet())

    init {
        viewModelScope.launch {
            user.collect { data ->
                _selectedTags.value = data?.tags ?: emptySet<Tag>()
            }
        }
    }

    fun addSelectedTag(tag: Tag) {
        _selectedTags.update { it + tag }
    }

    fun removeSelectedTag(tag: Tag) {
        _selectedTags.update { it - tag }
    }

    fun isTagSelected(tag: Tag): Boolean {
        return tag in _selectedTags.value
    }

    fun isDoneButtonEnabled(): Boolean {
        return _selectedTags.value.isNotEmpty()
    }

    fun onDoneButtonClick() {
        val userId = user.value?.id ?: return
        viewModelScope.launch {
            profileManager.updateTags(_selectedTags.value)
            userRepository.changeTags(userId, _selectedTags.value.map { it.getName() }.toSet())
        }
    }
}