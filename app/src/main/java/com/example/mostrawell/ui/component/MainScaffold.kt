package com.example.mostrawell.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mostrawell.R
import com.example.mostrawell.ui.navigation.Route

@Composable
fun MainScaffold(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
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
                    onClick = {
                        selectedScreen = 0
                        navController.navigate(Route.RecommendationFeedScreen.route)
                    },
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
                    onClick = {
                        selectedScreen = 1
                        navController.navigate(Route.ProfileScreen.route)
                    },
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
                    onClick = {
                        selectedScreen = 2
                        navController.navigate(Route.SettingsScreen.route)
                    },
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
        content(innerPadding)
    }
}