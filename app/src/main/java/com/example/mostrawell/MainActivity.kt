package com.example.mostrawell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mostrawell.ui.navigation.Root
import com.example.mostrawell.ui.navigation.Route
import com.example.mostrawell.ui.screen.interest_selection.InterestSelectionScreen
import com.example.mostrawell.ui.screen.register.RegisterScreen
import com.example.mostrawell.ui.screen.sign_in.SignInScreen
import com.example.mostrawell.ui.theme.MosTrawellTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MosTrawellTheme {
                Root()
            }
        }
    }
}