package com.example.mostrawell.ui.screen.interest_selection

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.entity.tag.Tag

class InterestSelectionViewModel: ViewModel() {
    val selectedTags = mutableStateListOf<Tag>()
    //TODO: Load user here

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