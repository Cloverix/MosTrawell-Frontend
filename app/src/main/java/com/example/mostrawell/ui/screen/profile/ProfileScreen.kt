package com.example.mostrawell.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mostrawell.R
import com.example.mostrawell.domain.util.findTagByName

@Composable
@Preview(showSystemUi = true)
fun ProfileScreen(
    modifier: Modifier = Modifier,
    model: ProfileViewModel = viewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (model.user == null) {
            CircularProgressIndicator(
                color = colorResource(R.color.main_color_lowered_contrast),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        else {
            val user = model.user!!
            var isEditModeActivated by rememberSaveable { mutableStateOf(false) }
            Scaffold(
                bottomBar = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 20.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .wrapContentSize()
                                //.clip(CircleShape)
                                .background(colorResource(R.color.main_color_lowered_contrast))
                                .clickable { /*TODO: navigate to tag editing screen*/ }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.note_pencil_icon),
                                    contentDescription = "Note and pencil icon"
                                )
                                Text(
                                    text = "Edit tags"
                                )
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .wrapContentSize()
                                //.clip(CircleShape)
                                .background(colorResource(R.color.main_color_lowered_contrast))
                                .clickable { isEditModeActivated = !isEditModeActivated }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.pencil_icon),
                                    contentDescription = "Pencil icon"
                                )
                                Text(
                                    text = "Edit profile"
                                )
                            }
                        }
                    }
                }
            ) { paddingValues ->
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = user.avatarUrl,
                        contentDescription = "User avatar",
                        placeholder = painterResource(R.drawable.placeholder_background),
                        fallback = painterResource(R.drawable.placeholder_background),
                        modifier = Modifier
                            .scale(0.5f)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                    OutlinedTextField(
                        value = user.name,
                        onValueChange = { /*TODO: add logic*/ },
                        readOnly = !isEditModeActivated,
                        label = { Text(text = "username") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = user.age,
                        onValueChange = { /*TODO: add logic*/ },
                        readOnly = !isEditModeActivated,
                        label = { Text(text = "age") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(Modifier.height(20.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        user.tags.forEach { tag ->
                            FilterChip(
                                selected = false,
                                onClick = {},
                                enabled = true,
                                label = { Text(text = tag.getName()) }
                            )
                        }
                    }
                }
            }
        }
    }
}