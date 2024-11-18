package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.videogamedbapp.ui.theme.Shapes

@Composable
fun VerticalGridShimmer(modifier: Modifier = Modifier) {
    CustomLazyVerticalGrid(count = 8, modifier = modifier.fillMaxSize()) {
        ShimmerEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .clip(Shapes.small)
                .background(Color.LightGray)
        )
    }
}