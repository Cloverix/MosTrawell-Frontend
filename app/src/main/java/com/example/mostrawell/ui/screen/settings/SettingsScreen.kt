package com.example.mostrawell.ui.screen.settings

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    model: SettingsViewModel = koinViewModel()
) {
    Log.d("TTT", "SettingsScreen launch")
    val uiState by model.uiState.collectAsStateWithLifecycle()
    val showDeleteAccountDialog by model.showDeleteAccountDialog.collectAsStateWithLifecycle()

    val deleteButtonColors = ButtonColors(
        containerColor = Color.Red,
        contentColor = Color.White,
        disabledContentColor = Color.White,
        disabledContainerColor = Color.Red.copy(alpha = 0.75f)
    )

    if (showDeleteAccountDialog) {
        AlertDialog(
            onDismissRequest = { model.toggleAlertDialog() },
            confirmButton = {
                Button(
                    onClick = {
                        model.toggleAlertDialog()
                        model.deleteUser()
                    },
                    colors = deleteButtonColors
                ) {
                    Text(
                        text = "Delete"
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { model.toggleAlertDialog() }
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
            },
            title = {
                Text(
                    text = "Delete account?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
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

    when(uiState) {
        is OperationResult.Success -> Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedButton(
                onClick = { model.toggleAlertDialog() },
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
        is OperationResult.Loading -> CircularProgressIndicator()
    }
}