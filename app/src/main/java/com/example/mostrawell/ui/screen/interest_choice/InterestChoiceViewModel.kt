package com.example.mostrawell.ui.screen.interest_choice

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.entity.tag.Tag

class InterestChoiceViewModel: ViewModel() {
    val chosenTags = mutableStateListOf<Tag>()
    //TODO: Load user here?

    fun addChosenTag(tag: Tag) {
        chosenTags.add(tag)
    }

    fun removeChosenTag(tag: Tag) {
        chosenTags.remove(tag)
    }

    fun isTagChosen(tag: Tag): Boolean {
        return tag in chosenTags
    }

    fun isDoneButtonEnabled(): Boolean {
        return chosenTags.isNotEmpty()
    }

    fun onDoneButtonClick() {
        //TODO: add chosen tags to user & update repository
    }
}