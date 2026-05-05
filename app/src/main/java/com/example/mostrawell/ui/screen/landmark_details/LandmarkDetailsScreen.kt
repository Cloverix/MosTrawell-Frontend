package com.example.mostrawell.ui.screen.landmark_details

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LandmarkDetailsScreen(
    landmarkId: Long
) {
    val model: LandmarkDetailsViewModel = koinViewModel(
        parameters = { parametersOf(landmarkId) }
    )


}