package com.example.mostrawell.ui.navigation

import android.util.Log
import androidx.compose.animation.core.Transition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.domain.util.AuthState
import com.example.mostrawell.ui.component.MainScaffold
import com.example.mostrawell.ui.screen.edit_profile.EditProfileScreen
import com.example.mostrawell.ui.screen.interest_selection.InterestSelectionScreen
import com.example.mostrawell.ui.screen.landmark_details.LandmarkDetailsScreen
import com.example.mostrawell.ui.screen.profile.ProfileScreen
import com.example.mostrawell.ui.screen.recommendation_feed.RecommendationFeedScreen
import com.example.mostrawell.ui.screen.register.RegisterScreen
import com.example.mostrawell.ui.screen.settings.SettingsScreen
import com.example.mostrawell.ui.screen.sign_in.SignInScreen
import org.koin.compose.koinInject

@Composable
fun Root(
    authState: AuthState = koinInject(),
    navController: NavHostController = rememberNavController()
) {
    val isUserLoggedIn by authState.isUserLoggedIn.collectAsStateWithLifecycle()

    if (isUserLoggedIn) {
        MainScaffold(navController) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.RecommendationFeedScreen.route
            ) {
                composable(
                    route = Route.EditProfileScreen.route
                ) {
                    EditProfileScreen(
                        navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable(Route.LandmarkDetailsScreen.route) { backStackEntry ->
                    val landmarkId = backStackEntry.arguments?.getString("landmark_id")?.toLong() ?: -1     //Иначе невалидный id
                    LandmarkDetailsScreen(
                        navController,
                        landmarkId,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable(Route.ProfileScreen.route) {
                    ProfileScreen(
                        navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable(Route.RecommendationFeedScreen.route) {
                    RecommendationFeedScreen(
                        navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable(Route.SettingsScreen.route) {
                    SettingsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable(Route.InterestSelection.route) {
                    InterestSelectionScreen(navController)
                }
            }
        }
    }
    else {
        NavHost(
            navController,
            startDestination = Route.SignInScreen.route
        ) {
            composable(Route.SignInScreen.route) {
                SignInScreen(
                    navController
                )
            }
            composable(Route.RegisterScreen.route) {
                RegisterScreen(
                    navController
                )
            }
            composable(Route.InterestSelection.route) {
                InterestSelectionScreen(navController)
            }
        }
    }
}