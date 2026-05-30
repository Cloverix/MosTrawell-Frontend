package com.example.mostrawell.ui.screen.recommendation_feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mostrawell.R
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.ui.component.composable.LandmarkCard
import com.example.mostrawell.ui.navigation.Route
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationFeedScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: RecommendationFeedViewModel = koinViewModel()
) {
    val query by model.query.collectAsStateWithLifecycle()
    val foundLandmarks by model.foundLandmarks.collectAsStateWithLifecycle()
    val uiState by model.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchBar(
            inputField = {
                InputField(
                    query = query,
                    onQueryChange = { model.onQueryChange(it) },
                    onSearch = { model.onSearchImmediate(it)},
                    expanded = false,
                    onExpandedChange = {},
                    trailingIcon = {
                        if (query.isEmpty()) {
                            Icon(
                                painter = painterResource(R.drawable.magnifying_glass_icon_bold),
                                contentDescription = "Magnifying glass icon",
                                modifier = Modifier
                                    .scale(0.75f)
                            )
                        }
                        else {
                            IconButton(
                                onClick = {
                                    model.clearInputField()
                                    model.onSearchImmediate("")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "clear_icon"
                                )
                            }
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Search for landmarks",
                            fontSize = 14.sp
                        )
                    }
                )
            },
            expanded = false,
            onExpandedChange = {}
        ) {}
        when(uiState) {
            is OperationResult.Success -> {
                if (foundLandmarks.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 20.dp)
                    ) {
                        items(foundLandmarks) { landmark ->
                            LandmarkCard(
                                landmark,
                                Modifier.clickable {
                                    navController.navigate(
                                        Route.LandmarkDetailsScreen.landmarkDetails(
                                            landmark.id
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                else {
                    Text(
                        text = "No landmarks found! Try a different query",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 20.dp)
                    )
                }
            }
            is OperationResult.Failure -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Text(
                        text = "An error occurred",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                    Text(
                        text = (uiState as OperationResult.Failure).message,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            is OperationResult.Loading -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}