package com.example.mostrawell.ui.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mostrawell.R
import com.example.mostrawell.domain.util.OperationResult
import org.intellij.lang.annotations.JdkConstants
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    model: SettingsViewModel = koinViewModel()
) {
    val uiState by model.uiState.collectAsStateWithLifecycle()
    val showDeleteAccountDialog by model.showDeleteAccountDialog.collectAsStateWithLifecycle()
    val showLogoutDialog by model.showLogoutDialog.collectAsStateWithLifecycle()

    val alertButtonColors = ButtonColors(
        containerColor = Color.Red,
        contentColor = Color.White,
        disabledContentColor = Color.White,
        disabledContainerColor = Color.Red.copy(alpha = 0.75f)
    )
    val confirmButtonColors = ButtonColors(
        containerColor = colorResource(R.color.main_color_lowered_contrast),
        contentColor = Color.White,
        disabledContentColor = colorResource(R.color.main_color_low_contrast).copy(alpha = 0.7f),
        disabledContainerColor = Color.White
    )

    if (showDeleteAccountDialog) {
        AlertDialog(
            onDismissRequest = { model.toggleDeleteDialog() },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(48.dp, alignment = Alignment.CenterHorizontally),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { model.toggleDeleteDialog() }
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }
                    Button(
                        onClick = {
                            model.toggleDeleteDialog()
                            model.deleteUser()
                        },
                        colors = alertButtonColors
                    ) {
                        Text(
                            text = "Delete"
                        )
                    }
                }
            },
            dismissButton = { },
            title = {
                Text(
                    text = "Delete account?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Please confirm account deletion"
                    )
                    Text(
                        text = "This action is irreversible"
                    )
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.info_icon),
                    contentDescription = "info icon"
                )
            }
        )
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { model.toggleLogoutDialog() },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(48.dp, alignment = Alignment.CenterHorizontally),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { model.toggleLogoutDialog() }
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }
                    Button(
                        onClick = {
                            model.toggleLogoutDialog()
                            model.logout()
                        },
                        colors = confirmButtonColors
                    ) {
                        Text(
                            text = "Log out"
                        )
                    }
                }
            },
            dismissButton = { },
            title = {
                Text(
                    text = "Log out?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            text = {
                Text(
                    text = "Confirm action",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.info_icon),
                    contentDescription = "info icon"
                )
            }
        )
    }

    when(uiState) {
        is OperationResult.Success -> Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedButton(
                onClick = { model.toggleLogoutDialog() },
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Log out",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            OutlinedButton(
                onClick = { model.toggleDeleteDialog() },
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(width = 1.dp, color = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Delete account",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red
                )
            }
        }
        is OperationResult.Failure -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = (uiState as OperationResult.Failure).message,
                    fontSize = 28.sp,
                    color = Color.Red
                )
            }
        }
        is OperationResult.Loading ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
    }
}