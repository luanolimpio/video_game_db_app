package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.videogamedbapp.R
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.ui.theme.Shapes

@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = Shapes.small
    ) {
        Column {
            SubcomposeAsyncImage(
                modifier = Modifier.height(128.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(game.backgroundImage)
                    .size(Size.ORIGINAL)
                    .crossfade(true).build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {
                    ShimmerEffect(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (isSystemInDarkTheme()) Color.Gray else Color.LightGray)
                    )
                },
                error = {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(R.drawable.ic_broken_image),
                        contentDescription = null
                    )
                },
            )
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                PlatformIcons(
                    parentPlatformIcons = game.parentPlatformIcons,
                    color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                    spacing = 4.dp,
                    additionalContent = {
                        game.metacritic?.let {
                            MetaScore(
                                score = it,
                                modifier = modifier.size(24.dp)
                            )
                        }
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = game.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}