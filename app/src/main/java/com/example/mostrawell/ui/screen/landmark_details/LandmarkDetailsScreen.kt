package com.example.mostrawell.ui.screen.landmark_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mostrawell.R
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.ui.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LandmarkDetailsScreen(
    navController: NavHostController,
    landmarkId: Long,
    modifier: Modifier = Modifier
) {
    val model: LandmarkDetailsViewModel = koinViewModel(
        parameters = { parametersOf(landmarkId) }
    )

    val uiState by model.uiState.collectAsStateWithLifecycle()
    val landmark by model.landmark.collectAsStateWithLifecycle()
    val isFavourite by model.isFavourite.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (uiState) {
            is OperationResult.Success -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                Text(
                    text = landmark!!.name,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 44.sp
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Address: " + landmark!!.address,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = landmark!!.desc,
                    fontSize = 20.sp
                )
                Spacer(Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    landmark!!.tags.forEach { tag ->
                        FilterChip(
                            selected = false,
                            onClick = {},
                            enabled = true,
                            label = { Text(text = tag.getFormattedName()) }
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
        FloatingActionButton(
            onClick = { model.onFavouritePressed() },
            shape = CircleShape,
            containerColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            val iconPainter = if (isFavourite) painterResource(R.drawable.heart_filled_icon) else painterResource(R.drawable.heart_icon)
            val iconTint = if (isFavourite) Color.Red else LocalContentColor.current
            Icon(
                painter = iconPainter,
                contentDescription = "heart icon",
                tint = iconTint
            )
        }
        FloatingActionButton(
            onClick = {
                if (!navController.popBackStack()) navController.navigate(Route.RecommendationFeedScreen.route)
            },
            containerColor = colorResource(R.color.main_color),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_icon),
                contentDescription = "arrow back icon"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    var isFavourite by remember { mutableStateOf(true) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = { isFavourite = !isFavourite },
            shape = CircleShape,
            containerColor = Color.White
        ) {
            val iconPainter = if (isFavourite) painterResource(R.drawable.heart_filled_icon) else painterResource(R.drawable.heart_icon)
            val iconTint = if (isFavourite) Color.Red else LocalContentColor.current
            Icon(
                painter = iconPainter,
                contentDescription = "heart icon",
                tint = iconTint
            )
        }
    }
}