package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.videogamedbapp.R
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.ui.theme.Shapes

@Composable
fun SearchCard(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(Shapes.small),
            model = ImageRequest.Builder(LocalContext.current)
                .data(game.backgroundImage)
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
        Spacer(modifier = Modifier.width(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            PlatformIcons(
                parentPlatformIcons = game.parentPlatformIcons,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                spacing = 4.dp,
            )
            Text(
                text = game.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        }
    }
}