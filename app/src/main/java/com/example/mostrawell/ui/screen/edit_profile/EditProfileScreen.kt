package com.example.mostrawell.ui.screen.edit_profile

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.ui.navigation.Route

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: EditProfileViewModel = viewModel()
) {
    Log.d("TTT", "EditProfileScreen launch")
    val name by model.name.collectAsStateWithLifecycle()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Edit name",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            OutlinedTextField(
                value = name,
                onValueChange = { newName -> model.onNameChange(newName) },
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        if (!focusState.hasFocus) {
                            model.onFocusLost()
                        }
                    }
            )
            Button(
                onClick = {
                    model.onDoneButtonClick()
                    navController.navigate(Route.ProfileScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = "Done"
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    EditProfileScreen(rememberNavController())
}