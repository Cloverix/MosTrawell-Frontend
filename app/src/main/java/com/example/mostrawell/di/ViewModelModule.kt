package com.example.mostrawell.di

import com.example.mostrawell.ui.screen.edit_profile.EditProfileViewModel
import com.example.mostrawell.ui.screen.interest_selection.InterestSelectionViewModel
import com.example.mostrawell.ui.screen.profile.ProfileViewModel
import com.example.mostrawell.ui.screen.recomendation_feed.RecommendationFeedViewModel
import com.example.mostrawell.ui.screen.register.RegisterViewModel
import com.example.mostrawell.ui.screen.sign_in.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditProfileViewModel(get(), get()) }
    viewModel { InterestSelectionViewModel(get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { RecommendationFeedViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    //SettingsViewModel
    viewModel { SignInViewModel(get()) }
}