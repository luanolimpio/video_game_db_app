package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.videogamedbapp.data.remote.dtos.Rating
import com.example.videogamedbapp.ui.theme.Shapes
import com.example.videogamedbapp.ui.theme.metacriticGreen
import com.example.videogamedbapp.ui.theme.metacriticRed
import com.example.videogamedbapp.ui.theme.metacriticYellow
import com.example.videogamedbapp.ui.theme.ratingBlue

@Composable
fun Rating(rating: Rating) {
    val color = getColorByRatingId(rating.id)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(Shapes.large)
                .background(color)
        )
        Text(
            text = "${rating.title.replaceFirstChar(Char::titlecase)}: ${rating.count}",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

private fun getColorByRatingId(id: Int): Color {
    return when (id) {
        1 -> metacriticRed
        3 -> metacriticYellow
        4 -> ratingBlue
        5 -> metacriticGreen
        else -> Color.DarkGray
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingPreview() {
    Rating(rating = Rating(count = 4150, id = 5, title = "exceptional", percent = 59.07))
}