package com.example.mostrawell.ui.screen.profile

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mostrawell.R
import com.example.mostrawell.ui.navigation.Route
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: ProfileViewModel = koinViewModel()
) {
    val user by model.user.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (user == null) {
            CircularProgressIndicator(
                color = colorResource(R.color.main_color_lowered_contrast),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        else {
            Scaffold(
                bottomBar = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 20.dp)
                    ) {
                        ExtendedFloatingActionButton(
                            onClick = { navController.navigate(Route.InterestSelection.route) },
                            text = { Text("Edit tags") },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.note_pencil_icon),
                                    contentDescription = "Note and pencil icon"
                                )
                            },
                            containerColor = colorResource(R.color.main_color_lowered_contrast).copy(alpha = 1f),
                            contentColor = Color.White
                        )
                        ExtendedFloatingActionButton(
                            onClick = { navController.navigate(Route.EditProfileScreen.route) },
                            text = { Text("Edit profile") },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.pencil_icon),
                                    contentDescription = "Pencil icon"
                                )
                            },
                            containerColor = colorResource(R.color.main_color_lowered_contrast).copy(alpha = 1f),
                            contentColor = Color.White
                        )
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
                        model = user!!.avatarUrl,
                        contentDescription = "User avatar",
                        placeholder = painterResource(R.drawable.placeholder_background),
                        fallback = painterResource(R.drawable.placeholder_background),
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                            .clickable(
                                onClick = { /*TODO:Photo selection logic via PhotoPicker*/ }
                            )
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = user!!.name,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold
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
                    Text(
                        text = "Your interest tags:",
                        fontSize = 16.sp
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        user!!.tags.forEach { tag ->
                            FilterChip(
                                selected = false,
                                onClick = {},
                                enabled = true,
                                label = { Text(text = tag.getFormattedName()) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    ProfileScreen(rememberNavController())
}