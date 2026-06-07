package com.example.mostrawell.ui.screen.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.R
import com.example.mostrawell.data.remote.AuthManager
import com.example.mostrawell.domain.util.AuthState
import com.example.mostrawell.domain.util.OperationResult
import com.example.mostrawell.domain.util.validateAge
import com.example.mostrawell.ui.component.composable.GradientMainScreen
import com.example.mostrawell.ui.component.defaults.defaultButtonColors
import com.example.mostrawell.ui.navigation.Route
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    authState: AuthState = koinInject(),
    modifier: Modifier = Modifier,
    model: RegisterViewModel = koinViewModel()
    ) {
    val context = LocalContext.current

    val unmaskPasswordField by model.unmaskPasswordField.collectAsStateWithLifecycle()
    val unmaskDuplicatePasswordField by model.unmaskDuplicatePasswordField.collectAsStateWithLifecycle()
    val uiState by model.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(Modifier.height(40.dp))
            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = model.nickname,
                    onValueChange = { newName -> model.onNicknameChange(newName) },
                    label = { Text(text = "Nickname") },
                    modifier = Modifier
                        .weight(3f)
                )
                OutlinedTextField(
                    value = model.age,
                    onValueChange = { newAge -> model.onAgeChange(newAge) },
                    label = { Text(text = "Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .onFocusChanged { focusState ->
                            if (!focusState.isFocused && model.age.isNotBlank()) {
                                val validatedAge: String? = validateAge(model.age)
                                if (validatedAge == null) {
                                    Toast.makeText(
                                        context,
                                        R.string.not_valid_age_message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    model.onAgeChange("")
                                }
                            }
                        }
                        .weight(1f)
                )
            }
            OutlinedTextField(
                value = model.login,
                onValueChange = { newLogin -> model.onLoginChange(newLogin) },
                label = { Text(text = "Login") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = model.password,
                onValueChange = { newPassword -> model.onPasswordChange(newPassword) },
                visualTransformation = if (!unmaskPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
                label = { Text(text = "Password") },
                trailingIcon = {
                    IconButton(
                        onClick = { model.onUnmaskPasswordField() }
                    ) {
                        val iconPainter =
                            if (unmaskPasswordField) painterResource(R.drawable.eye_icon)
                            else painterResource(R.drawable.eye_slash_icon)
                        Icon(
                            painter = iconPainter,
                            contentDescription = "eye icon"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = model.duplicatePassword,
                onValueChange = { newPassword -> model.onDuplicatePasswordChange(newPassword) },
                visualTransformation = if (!unmaskDuplicatePasswordField) PasswordVisualTransformation() else VisualTransformation.None,
                label = { Text(text = "Repeat password") },
                trailingIcon = {
                    IconButton(
                        onClick = { model.onUnmaskDuplicatePasswordField() }
                    ) {
                        val iconPainter =
                            if (unmaskDuplicatePasswordField) painterResource(R.drawable.eye_icon)
                            else painterResource(R.drawable.eye_slash_icon)
                        Icon(
                            painter = iconPainter,
                            contentDescription = "eye icon"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    model.viewModelScope.launch {
                        try {
                            when (val registrationResult = model.onDoneButtonClick()) {
                                is OperationResult.Success -> authState.setLoggedInState(true)
                                is OperationResult.Failure -> Toast.makeText(
                                    context,
                                    registrationResult.message,
                                    Toast.LENGTH_LONG
                                ).show()

                                else -> {}
                            }
                        }
                        catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = defaultButtonColors(),
                enabled = model.isDoneButtonEnabled(),
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(CircleShape)
                    .padding(vertical = 20.dp)
            ) {
                when (uiState) {
                    is OperationResult.Success -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Done",
                            fontSize = 16.sp
                        )
                        Icon(
                            painter = painterResource(R.drawable.check_bold_icon),
                            contentDescription = "Check icon",
                            modifier = Modifier
                                .scale(0.75f)
                        )
                    }
                    is OperationResult.Failure -> {}
                    is OperationResult.Loading -> CircularProgressIndicator()
                }
            }
            Text(
                text = "Already have an account?",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            OutlinedButton(
                onClick = { navController.navigate(Route.SignInScreen.route) },
                colors = ButtonColors(
                    containerColor = Color(0, 0, 0, 0),
                    contentColor = colorResource(R.color.black),
                    disabledContentColor = colorResource(R.color.black),
                    disabledContainerColor = Color(0, 0, 0, 0)
                ),
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    RegisterScreen(rememberNavController(), model = viewModel())
}