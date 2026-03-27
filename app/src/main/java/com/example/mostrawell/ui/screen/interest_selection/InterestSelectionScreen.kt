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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.R
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.LocationTag
import com.example.mostrawell.ui.component.SimpleScaffold

@Composable
fun InterestSelectionScreen(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        model: InterestSelectionViewModel = viewModel()
    ) {
    SimpleScaffold{ paddingValues ->
        Column(
            modifier = Modifier
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
                        selected = model.isTagSelected(tag),
                        onClick = {
                            if (model.isTagSelected(tag)) model.removeSelectedTag(tag)
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
                        selected = model.isTagSelected(tag),
                        onClick = {
                            if (model.isTagSelected(tag)) model.removeSelectedTag(tag)
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
            OutlinedButton(
                onClick = { model.onDoneButtonClick() },
                enabled = model.isDoneButtonEnabled(),
                colors = ButtonColors(
                    containerColor = colorResource(R.color.main_color),
                    contentColor = colorResource(R.color.white),
                    disabledContainerColor = Color(0, 0, 0, 0),
                    disabledContentColor = colorResource(R.color.black)
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Done"
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    InterestSelectionScreen(rememberNavController(), model = viewModel())
}