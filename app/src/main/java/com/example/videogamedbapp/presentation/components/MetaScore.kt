package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.videogamedbapp.ui.theme.Grey10
import com.example.videogamedbapp.ui.theme.Shapes
import com.example.videogamedbapp.ui.theme.metacriticGreen
import com.example.videogamedbapp.ui.theme.metacriticRed
import com.example.videogamedbapp.ui.theme.metacriticYellow

@Composable
fun MetaScore(score: Int, modifier: Modifier = Modifier) {
    val color = getColorByScore(score)
    Box(
        modifier = modifier
            .clip(Shapes.small)
            .background(color)

    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "$score",
            style = TextStyle(
                color = if (color == metacriticRed) Color.White else Grey10,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

private fun getColorByScore(score: Int): Color {
    return when (score) {
        in 75..100 -> metacriticGreen
        in 50..74 -> metacriticYellow
        else -> metacriticRed
    }
}

@Preview(showBackground = true)
@Composable
private fun MetaScorePreview() {
    MetaScore(score = 92)
}