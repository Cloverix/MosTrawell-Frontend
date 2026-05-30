package com.example.mostrawell.ui.screen.edit_profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.R
import com.example.mostrawell.ui.component.defaults.defaultButtonColors
import com.example.mostrawell.ui.navigation.Route
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: EditProfileViewModel = koinViewModel()
) {
    val name by model.name.collectAsStateWithLifecycle()
    val context = LocalContext.current

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
                maxLines = 1,
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        if (!focusState.hasFocus) {
                            model.onFocusLost()
                        }
                    }
            )
            Button(
                onClick = {
                    if (name.isBlank()) {
                        Toast.makeText(context, "Name must not be blank", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        model.onDoneButtonClick()
                        if (!navController.popBackStack()) navController.navigate(Route.ProfileScreen.route)
                    }
                },
                colors = defaultButtonColors(),
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