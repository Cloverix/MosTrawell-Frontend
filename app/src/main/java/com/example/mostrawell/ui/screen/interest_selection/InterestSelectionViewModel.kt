package com.example.mostrawell.ui.screen.interest_selection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.ui.model.UserUiModel

class InterestSelectionViewModel(user: UserUiModel?): ViewModel() {
    var user by mutableStateOf<UserUiModel?>(user)
    val selectedTags = mutableStateListOf<Tag>(*user?.tags?.toTypedArray() ?: emptyList<Tag>().toTypedArray())

    fun addSelectedTag(tag: Tag) {
        selectedTags.add(tag)
    }

    fun removeSelectedTag(tag: Tag) {
        selectedTags.remove(tag)
    }

    fun isTagSelected(tag: Tag): Boolean {
        return tag in selectedTags
    }

    fun isDoneButtonEnabled(): Boolean {
        return selectedTags.isNotEmpty()
    }

    fun onDoneButtonClick() {
        //TODO: add selected tags to user & update repository
    }
}