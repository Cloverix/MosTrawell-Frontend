package com.example.mostrawell.ui.screen.landmark_details

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mostrawell.R
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.ui.navigation.Route
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LandmarkDetailsScreen(
    navController: NavHostController,
    landmarkId: Long,
    modifier: Modifier = Modifier
) {
    Log.d("TTT", "LandmarkDetailsScreen launch")
    val model: LandmarkDetailsViewModel = koinViewModel(
        parameters = { parametersOf(landmarkId) }
    )

    val uiState by model.uiState.collectAsStateWithLifecycle()
    val landmark by model.landmark.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (uiState) {
            is OperationResult.Success -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                Text(
                    text = landmark!!.name,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Address: " + landmark!!.address,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = landmark!!.desc,
                    fontSize = 20.sp
                )
                Spacer(Modifier.height(20.dp))
                FlowRow {
                    landmark!!.tags.forEach { tag ->
                        FilterChip(
                            selected = false,
                            onClick = {},
                            enabled = true,
                            label = { Text(text = tag.getName()) }
                        )
                    }
                }
            }
            is OperationResult.Failure -> Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = "An error occurred",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                    Text(
                        text = (uiState as OperationResult.Failure).message
                    )
            }
            is OperationResult.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        IconButton(
            onClick = { navController.navigate(Route.RecommendationFeedScreen.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_icon),
                contentDescription = "arrow back icon"
            )
        }
    }
}