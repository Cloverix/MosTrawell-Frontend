package com.example.mostrawell.ui.component.defaults

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.mostrawell.R

@Composable
fun defaultButtonColors(): ButtonColors = ButtonColors(
    containerColor = colorResource(R.color.main_color),
    contentColor = Color.White,
    disabledContentColor = Color.White,
    disabledContainerColor = colorResource(R.color.main_color_lowered_contrast)
)