package com.example.mostrawell.ui.navigation

sealed class Route(val route: String) {
    object Register: Route("register")
    object SignIn: Route("sign_in")
    object InterestSelection: Route("interest_selection")

    //Example for a route with arguments:
    /*
    object UserDetails: Route("user_details/{userId}") {
        fun userDetails(userId: Long) = "user_details/$userId"
    }
    */
}