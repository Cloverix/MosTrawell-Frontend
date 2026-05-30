package com.example.mostrawell.ui.navigation.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.ui.navigation.Route
import com.example.mostrawell.ui.screen.edit_profile.EditProfileScreen
import com.example.mostrawell.ui.screen.interest_selection.InterestSelectionScreen
import com.example.mostrawell.ui.screen.profile.ProfileScreen

@Composable
fun ProfilePage() {
    val localNavController = rememberNavController()
    NavHost(
        navController = localNavController,
        startDestination = Route.ProfileScreen.route
    ) {
        composable(Route.ProfileScreen.route) {
            ProfileScreen(localNavController)
        }
        composable(Route.EditProfileScreen.route) {
            EditProfileScreen(localNavController)
        }
        composable(Route.InterestSelection.route) {
            InterestSelectionScreen(localNavController)
        }
    }
}