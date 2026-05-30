package com.example.mostrawell.ui.navigation.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.ui.navigation.Route
import com.example.mostrawell.ui.screen.landmark_details.LandmarkDetailsScreen
import com.example.mostrawell.ui.screen.recommendation_feed.RecommendationFeedScreen

@Composable
fun RecommendationFeedPage() {
    val localNavController = rememberNavController()
    NavHost(
        navController = localNavController,
        startDestination = Route.RecommendationFeedScreen.route
    ) {
        composable(Route.RecommendationFeedScreen.route) {
            RecommendationFeedScreen(localNavController)
        }
        composable(
            Route.LandmarkDetailsScreen.route
        ) { backStackEntry ->
            val landmarkId = backStackEntry.arguments?.getString("landmark_id")?.toLong() ?: -1
            LandmarkDetailsScreen(localNavController, landmarkId)
        }
    }
}