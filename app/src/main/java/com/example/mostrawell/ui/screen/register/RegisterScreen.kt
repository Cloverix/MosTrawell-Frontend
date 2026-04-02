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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.R
import com.example.mostrawell.ui.component.GradientMainScreen
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlin.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        model: RegisterViewModel = viewModel()
    ) {
    val context = LocalContext.current

    GradientMainScreen(
        gradientColor1 = colorResource(R.color.main_color),
        gradientColor2 = colorResource(R.color.white)
    ) {
        Box(
            modifier = Modifier
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
                                    val validatedAge: String? = model.validateAge(model.age)
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
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = model.duplicatePassword,
                    onValueChange = { newPassword -> model.onDuplicatePasswordChange(newPassword) },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Repeat password") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = { model.onDoneButtonClick(navController) },
                    colors = ButtonColors(
                        containerColor = colorResource(R.color.main_color),
                        contentColor = colorResource(R.color.black),
                        disabledContentColor = colorResource(R.color.black),
                        disabledContainerColor = colorResource(R.color.main_color).copy(alpha = 0.7f)
                    ),
                    enabled = model.isDoneButtonEnabled(),
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(CircleShape)
                        .padding(vertical = 20.dp)
                ) {
                    Row(
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
                }
                Text(
                    text = "Already have an account?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedButton(
                    onClick = { model.onSignInButtonClick(navController) },
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
}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    RegisterScreen(rememberNavController(), model = viewModel())
}