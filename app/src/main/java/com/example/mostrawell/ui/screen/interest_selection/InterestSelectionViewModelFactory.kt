package com.example.mostrawell.ui.screen.interest_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mostrawell.ui.model.UserUiModel

class InterestSelectionViewModelFactory(private val user: UserUiModel?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InterestSelectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InterestSelectionViewModel(user) as T
        }
        throw IllegalArgumentException("Illegal argument for ViewModel factory")
    }
}