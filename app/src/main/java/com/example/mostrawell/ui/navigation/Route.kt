package com.example.mostrawell.ui.navigation

sealed class Route(val route: String) {
    object EditProfileScreen: Route("edit_profile")
    object InterestSelection: Route("interest_selection")
    object LandmarkDetails: Route("landmark_details/{landmark_id}") {
        fun landmarkDetails(landmarkId: Long) = "landmark_details/$landmarkId"
    }
    object ProfileScreen: Route("profile")
    object RecommendationFeedScreen: Route("recommendation_feed")
    object Register: Route("register")
    object SignIn: Route("sign_in")
    object EditTagsScreen: Route("edit_tags")

    //Example for a route with arguments:
    /*
    object UserDetails: Route("user_details/{userId}") {
        fun userDetails(userId: Long) = "user_details/$userId"
    }
    */
}