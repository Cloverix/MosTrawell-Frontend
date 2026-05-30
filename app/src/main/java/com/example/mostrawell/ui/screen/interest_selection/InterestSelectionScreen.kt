package com.example.mostrawell.ui.screen.interest_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.R
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.LocationTag
import com.example.mostrawell.domain.util.AuthState
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.ui.component.composable.SimpleScaffold
import com.example.mostrawell.ui.component.defaults.defaultButtonColors
import com.example.mostrawell.ui.navigation.Route
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun InterestSelectionScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authState: AuthState = koinInject(),
    model: InterestSelectionViewModel = koinViewModel()
) {
    val uiState by model.uiState.collectAsStateWithLifecycle()
    val selectedTags by model.selectedTags.collectAsStateWithLifecycle()

    SimpleScaffold { paddingValues ->
        when(uiState) {
            is OperationResult.Failure -> Text(
                text = (uiState as OperationResult.Failure).message,
                fontSize = 24.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(vertical = 40.dp)
            )
            else -> Column(
                modifier = modifier
                    .padding(paddingValues)
                    .padding(horizontal = 5.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Choose some interests that describe you!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(R.color.grey),
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(0.95f)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Entertainment",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    EntertainmentTag.entries.forEach { tag ->
                        FilterChip(
                            selected = tag in selectedTags,
                            onClick = {
                                if (tag in selectedTags) model.removeSelectedTag(tag)
                                else model.addSelectedTag(tag)
                            },
                            label = {
                                Text(
                                    text = tag.name
                                        .replace("_", " ")
                                        .lowercase()
                                        .replaceFirstChar { it.uppercase() }
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = colorResource(R.color.main_color),
                                selectedLabelColor = colorResource(R.color.white),
                                containerColor = colorResource(R.color.white),
                                labelColor = colorResource(R.color.black)
                            )
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(R.color.grey),
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(0.95f)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Location",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    LocationTag.entries.forEach { tag ->
                        FilterChip(
                            selected = tag in selectedTags,
                            onClick = {
                                if (tag in selectedTags) model.removeSelectedTag(tag)
                                else model.addSelectedTag(tag)
                            },
                            label = {
                                Text(
                                    text = tag.getFormattedName()
                                        .replace("_", " ")
                                        .lowercase()
                                        .replaceFirstChar { it.uppercase() }
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = colorResource(R.color.main_color),
                                selectedLabelColor = colorResource(R.color.white),
                                containerColor = colorResource(R.color.white),
                                labelColor = colorResource(R.color.black)
                            )
                        )
                    }
                }
                OutlinedButton(
                    onClick = {
                        val toNavigate = authState.getLoggedInState()
                        model.onDoneButtonClick()
                        when (uiState) {
                            is OperationResult.Success -> {
                                if (toNavigate) {
                                    if (!navController.popBackStack()) navController.navigate(Route.ProfileScreen.route)
                                }
                                else {
                                    authState.setLoggedInState(true)
                                }
                            }
                            else -> {}
                        }
                    },
                    enabled = selectedTags.isNotEmpty() && uiState is OperationResult.Success,
                    colors = defaultButtonColors(),
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    when (uiState) {
                        is OperationResult.Loading -> CircularProgressIndicator()
                        else -> Text(
                            text = "Done"
                        )
                    }

                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    InterestSelectionScreen(rememberNavController(), authState = AuthState())
}