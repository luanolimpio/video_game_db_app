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
import com.example.videogamedbapp.ui.theme.Shapes

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
                color = if (color == Color.Red) Color.White else Color.Unspecified,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

private fun getColorByScore(score: Int): Color {
    return when (score) {
        in 75..100 -> Color.Green
        in 50..74 -> Color.Yellow
        else -> Color.Red
    }
}

@Preview(showBackground = true)
@Composable
private fun MetaScorePreview() {
    MetaScore(score = 92)
}