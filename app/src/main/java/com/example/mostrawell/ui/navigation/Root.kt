package com.example.mostrawell.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.R
import com.example.mostrawell.ui.screen.recomendation_feed.RecommendationFeedScreen

@Preview(showSystemUi = true)
@Composable
fun Root(navController: NavHostController = rememberNavController()) {
    var userSignedIn by rememberSaveable { mutableStateOf(true) }      //TODO: ТОЛЬКО ДЛЯ ТЕСТА, убрать позже
    var selectedScreen by rememberSaveable { mutableIntStateOf(0) }
    val navigationBarItemColors = NavigationBarItemColors(
        selectedIndicatorColor = colorResource(R.color.main_color_lowered_contrast),
        selectedTextColor = Color.Unspecified,
        selectedIconColor = Color.Unspecified,
        unselectedIconColor = Color.Unspecified,
        unselectedTextColor = Color.Unspecified,
        disabledIconColor = Color.Unspecified,
        disabledTextColor = Color.Unspecified
    )

    if (userSignedIn) {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            colorResource(R.color.main_color_lowered_contrast)
                        )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = colorResource(R.color.main_color_low_contrast)
                ) {
                    NavigationBarItem(
                        selected = selectedScreen == 0,
                        onClick = {selectedScreen = 0},
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.magnifying_glass_icon),
                                contentDescription = "Magnifying glass icon",
                                tint = Color.Black
                            )
                        },
                        label = { Text(text = "Feed") },
                        colors = navigationBarItemColors
                    )
                    NavigationBarItem(
                        selected = selectedScreen == 1,
                        onClick = {selectedScreen = 1},
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.user_icon),
                                contentDescription = "User icon",
                                tint = Color.Black
                            )
                        },
                        label = { Text(text = "Profile") },
                        colors = navigationBarItemColors
                    )
                    NavigationBarItem(
                        selected = selectedScreen == 2,
                        onClick = {selectedScreen = 2},
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.gear_icon),
                                contentDescription = "Gear icon",
                                tint = Color.Black
                            )
                        },
                        label = { Text(text = "Settings") },
                        colors = navigationBarItemColors
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.RecommendationFeedScreen.route
            ) {
                composable(Route.RecommendationFeedScreen.route) {
                    RecommendationFeedScreen()
                }
            }
        }
    }
    /*
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
    */
}