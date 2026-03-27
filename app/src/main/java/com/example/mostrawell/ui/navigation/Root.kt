package com.example.mostrawell.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.ui.screen.interest_selection.InterestSelectionScreen
import com.example.mostrawell.ui.screen.register.RegisterScreen
import com.example.mostrawell.ui.screen.sign_in.SignInScreen

@Composable
fun Root(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.Register.route
    ) {
        composable(Route.Register.route) {
            RegisterScreen(navController)
        }
        composable(Route.SignIn.route) {
            SignInScreen(navController)
        }
        composable(Route.InterestSelection.route) {
            InterestSelectionScreen(navController)
        }
    }
}