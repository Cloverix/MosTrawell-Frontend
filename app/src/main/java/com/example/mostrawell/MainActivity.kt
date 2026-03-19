package com.example.mostrawell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.mostrawell.ui.screen.register.RegisterScreen
import com.example.mostrawell.ui.theme.MosTrawellTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MosTrawellTheme {
                RegisterScreen(Modifier)
            }
        }
    }
}