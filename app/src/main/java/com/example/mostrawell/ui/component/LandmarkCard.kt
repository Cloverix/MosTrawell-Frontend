package com.example.mostrawell.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.LocationTag
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.ui.model.LandmarkUiModel

@Composable
fun LandmarkCard(
    landmark: LandmarkUiModel,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        border = BorderStroke(width = 1.dp, color = Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .shadow(elevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Text(
                text = landmark.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Text(
                text = "Address: " + landmark.address,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Text(
                text = landmark.desc,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Start)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    val landmark = LandmarkUiModel(
        id = 13,
        name = "Московский кремль",
        address = "Красная площадь",
        desc = "Древнейшая крепость, исторический, политический и духовный центр России",
        tags = setOf<Tag>(
            LocationTag.MUSEUM,
            EntertainmentTag.HISTORY
        )
    )
    val listLandmarks = mutableListOf<LandmarkUiModel>()
    repeat(5) { listLandmarks.add(landmark) }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 50.dp)
    ) {
        items(listLandmarks) { item ->
            LandmarkCard(item)
        }
    }
}