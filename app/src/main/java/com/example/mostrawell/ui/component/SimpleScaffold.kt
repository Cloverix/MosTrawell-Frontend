package com.example.mostrawell.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mostrawell.R

@Composable
fun SimpleScaffold(modifier: Modifier = Modifier, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar =  {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(colorResource(R.color.main_color))
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(colorResource(R.color.main_color))
            )
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}