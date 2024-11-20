package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.videogamedbapp.ui.theme.Shapes

@Composable
fun GameDetailsShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ShimmerEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .background(if (isSystemInDarkTheme()) Color.Gray else Color.LightGray)
        )
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 1..3){
                ShimmerEffect(
                    modifier = Modifier
                        .width(150.dp)
                        .height(20.dp)
                        .clip(Shapes.small)
                        .background(if (isSystemInDarkTheme()) Color.Gray else Color.LightGray)
                )
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .clip(Shapes.small)
                        .background(Color.LightGray)
                )
            }
        }
    }
}