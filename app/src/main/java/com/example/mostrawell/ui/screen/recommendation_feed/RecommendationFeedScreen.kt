package com.example.mostrawell.ui.screen.recommendation_feed

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mostrawell.R
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.LocationTag
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.ui.component.LandmarkCard
import com.example.mostrawell.ui.model.LandmarkUiModel
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
                    onSearch = { model.onSearch(it) },
                    expanded = false,
                    onExpandedChange = {},
                    trailingIcon = {
                        IconButton(
                            onClick = { model.onSearch(query) }
                        ) {
                            Icon(
                            painter = painterResource(R.drawable.magnifying_glass_icon_bold),
                            contentDescription = "Magnifying glass icon",
                            modifier = Modifier
                                .scale(0.75f)
                            )
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
            is OperationResult.Success -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 20.dp)
            ) {
                items(foundLandmarks) { landmark ->
                    LandmarkCard(
                        landmark,
                        Modifier.clickable { navController.navigate(Route.LandmarkDetails.landmarkDetails(landmark.id)) }
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
                        text = "An error occured",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                    Text(
                        text = (uiState as OperationResult.Failure).message
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Preview() {
    val testLandmarks = mutableListOf(
        LandmarkUiModel(
            id = 1,
            name = "Московский кремль",
            address = "Красная площадь",
            desc = "Древнейшая крепость, исторический, политический и духовный центр России",
            tags = setOf(LocationTag.MUSEUM, EntertainmentTag.HISTORY)
        ),
        LandmarkUiModel(
            id = 2,
            name = "Винзавод",
            address = "4 Сыромятнический пер., 1/8 строение 6",
            desc = "Центр современного искусства",
            tags = setOf(LocationTag.MUSEUM, EntertainmentTag.MODERN_ARTS)
        )
    )
    for (i in 0..5) {
        testLandmarks.addAll(testLandmarks)
    }
    var query by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar(
            inputField = {
                InputField(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = {},
                    trailingIcon = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.magnifying_glass_icon_bold),
                                contentDescription = "Magnifying glass icon",
                                modifier = Modifier
                                    .scale(0.75f)
                            )
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
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 20.dp)
        ) {
            items(testLandmarks) { landmark ->
                LandmarkCard(
                    landmark,
                    Modifier.clickable {}
                )
            }
        }
    }
}