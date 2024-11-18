package com.example.videogamedbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.videogamedbapp.presentation.navigation.MainNavigator
import com.example.videogamedbapp.ui.theme.VideoGameDBAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoGameDBAppTheme {
                MainNavigator()
            }
        }
    }
}
