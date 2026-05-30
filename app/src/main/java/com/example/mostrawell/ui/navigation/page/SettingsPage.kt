package com.example.mostrawell.ui.navigation.page

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.ui.navigation.Route
import com.example.mostrawell.ui.screen.settings.SettingsScreen

@Composable
fun SettingsPage() {
    val localNavController = rememberNavController()
    NavHost(
        navController = localNavController,
        startDestination = Route.SettingsScreen.route
    ) {
        composable(Route.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}