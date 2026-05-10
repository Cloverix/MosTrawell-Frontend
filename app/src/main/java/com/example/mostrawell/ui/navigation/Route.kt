package com.example.mostrawell.ui.navigation

sealed class Route(val route: String) {
    object EditProfileScreen: Route("edit_profile")
    object InterestSelection: Route("interest_selection")
    object LandmarkDetailsScreen: Route("landmark_details/{landmark_id}") {
        fun landmarkDetails(landmarkId: Long) = "landmark_details/$landmarkId"
    }
    object ProfileScreen: Route("profile")
    object RecommendationFeedScreen: Route("recommendation_feed")
    object RegisterScreen: Route("register")
    object SettingsScreen: Route("settings")
    object SignInScreen: Route("sign_in")
    object EditTagsScreen: Route("edit_tags")
}