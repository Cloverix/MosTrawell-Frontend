package com.example.mostrawell.ui.component.composable

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.mostrawell.R
import kotlinx.coroutines.launch

@Composable
fun MainNavigationBar(
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    val navigationBarItemColors = NavigationBarItemColors(
        selectedIndicatorColor = colorResource(R.color.main_color_lowered_contrast),
        selectedTextColor = Color.Unspecified,
        selectedIconColor = Color.Unspecified,
        unselectedIconColor = Color.Unspecified,
        unselectedTextColor = Color.Unspecified,
        disabledIconColor = Color.Unspecified,
        disabledTextColor = Color.Unspecified
    )

    NavigationBar(
        containerColor = colorResource(R.color.main_color_low_contrast)
    ) {
        NavigationBarItem(
            selected = pagerState.currentPage == 0,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(0)
                }
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
            selected = pagerState.currentPage == 1,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(1)
                }
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
            selected = pagerState.currentPage == 2,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(2)
                }
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