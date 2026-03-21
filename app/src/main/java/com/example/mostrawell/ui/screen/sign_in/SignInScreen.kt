package com.example.mostrawell.ui.screen.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mostrawell.R
import com.example.mostrawell.ui.component.GradientMainScreen

@Composable
fun SignInScreen(modifier: Modifier, model: SignInViewModel = viewModel()) {
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
                    .align(Alignment.Center)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(Modifier.height(40.dp))
                TextField(
                    value = model.login,
                    onValueChange = { newLogin -> model.onLoginChange(newLogin) },
                    label = { Text(text = "Login") },
                    modifier = Modifier
                )
                TextField(
                    value = model.password,
                    onValueChange = { newPassword -> model.onPasswordChange(newPassword) },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Password") },
                    modifier = Modifier
                )
                Button(
                    onClick = { model.onDoneButtonClick() },
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
                    text = "Don't have an account?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedButton(
                    onClick = { model.onSignUpButtonClick() },
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
                        text = "Sign up",
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
    SignInScreen(Modifier, viewModel())
}