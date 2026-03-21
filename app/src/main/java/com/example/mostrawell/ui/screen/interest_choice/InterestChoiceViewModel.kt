package com.example.mostrawell.ui.screen.interest_choice

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mostrawell.domain.entity.tag.Tag

class InterestChoiceViewModel: ViewModel() {
    val chosenTags = mutableStateListOf<Tag>()

    fun addChosenTag(tag: Tag) {
        chosenTags.add(tag)
    }

    fun removeChosenTag(tag: Tag) {
        chosenTags.remove(tag)
    }

    fun isTagChosen(tag: Tag): Boolean {
        return tag in chosenTags
    }
}