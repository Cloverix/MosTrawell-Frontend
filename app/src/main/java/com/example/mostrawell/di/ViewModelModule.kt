    package com.example.mostrawell.di

    import com.example.mostrawell.ui.screen.edit_profile.EditProfileViewModel
    import com.example.mostrawell.ui.screen.interest_selection.InterestSelectionViewModel
    import com.example.mostrawell.ui.screen.landmark_details.LandmarkDetailsViewModel
    import com.example.mostrawell.ui.screen.profile.ProfileViewModel
    import com.example.mostrawell.ui.screen.recommendation_feed.RecommendationFeedViewModel
    import com.example.mostrawell.ui.screen.register.RegisterViewModel
    import com.example.mostrawell.ui.screen.settings.SettingsViewModel
    import com.example.mostrawell.ui.screen.sign_in.SignInViewModel
    import org.koin.core.module.dsl.viewModel
    import org.koin.dsl.module

    val viewModelModule = module {
        viewModel { EditProfileViewModel(get(), get()) }
        viewModel { InterestSelectionViewModel(get(), get(), get()) }
        viewModel { params ->
            LandmarkDetailsViewModel(
                landmarkId = params.get(),
                landmarkRepository = get()
            )
        }
        viewModel { ProfileViewModel(get()) }
        viewModel { RecommendationFeedViewModel(get(), get()) }
        viewModel { RegisterViewModel(get(), get()) }
        viewModel { SettingsViewModel(get(), get(), get()) }
        viewModel { SignInViewModel(get(), get()) }
    }