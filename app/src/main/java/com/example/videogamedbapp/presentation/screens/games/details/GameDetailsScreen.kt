package com.example.videogamedbapp.presentation.screens.games.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.videogamedbapp.R
import com.example.videogamedbapp.domain.models.GameDetails
import com.example.videogamedbapp.presentation.components.GameDetailsShimmer
import com.example.videogamedbapp.presentation.components.ShimmerEffect
import com.example.videogamedbapp.presentation.components.CustomTopAppBar
import com.example.videogamedbapp.presentation.components.ErrorState
import com.example.videogamedbapp.presentation.components.MetaScore
import com.example.videogamedbapp.presentation.components.PlatformIcons
import com.example.videogamedbapp.presentation.components.Rating
import com.example.videogamedbapp.presentation.components.TopAppBarIcon
import com.example.videogamedbapp.ui.theme.Grey10
import com.example.videogamedbapp.ui.theme.Shapes

@Composable
fun GameDetailsScreen(
    state: GameDetailsUiState,
    onNavigateBack: () -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.details),
                navigationIcon = TopAppBarIcon(
                    icon = Icons.AutoMirrored.Default.ArrowBack,
                    onClick = onNavigateBack
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (state) {
                is GameDetailsUiState.Loading -> {
                    GameDetailsShimmer()
                }

                is GameDetailsUiState.Success -> {
                    Details(game = state.game)
                }

                is GameDetailsUiState.Error -> {
                    ErrorState(
                        message = state.message,
                        onRefresh = onRefresh
                    )
                }

                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Details(game: GameDetails) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .height(256.dp)
                .fillMaxWidth(),
        ) {
            if (game.backgroundImage != null) {
                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(game.backgroundImage)
                        .crossfade(true).build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.Gray.copy(0.7f),
                        BlendMode.Modulate
                    ),
                    loading = {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                        )
                    },
                    error = {
                        Icon(
                            painter = painterResource(R.drawable.ic_broken_image),
                            contentDescription = null
                        )
                    },
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray.copy(0.7f))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(
                    text = game.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                if (game.playtime > 0) {
                    Text(
                        text = "${stringResource(R.string.average_playtime)}: ${game.playtime} ${
                            stringResource(
                                if (game.playtime > 1) R.string.hours else R.string.hour
                            )
                        }",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (game.released != null) {
                    Box(
                        modifier = Modifier
                            .clip(Shapes.small)
                            .background(Color.White)
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = game.released,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Grey10,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                PlatformIcons(
                    parentPlatformIcons = game.parentPlatformIcons,
                    color = Color.White,
                    spacing = 8.dp,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    game.ratings.forEach { item ->
                        Rating(rating = item)
                    }
                }
            }
        }
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (game.description.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.about),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = game.description)
            }
            Text(
                text = stringResource(R.string.platforms),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = game.platforms.joinToString())
            game.metacritic?.let {
                Text(
                    text = stringResource(R.string.metascore),
                    style = MaterialTheme.typography.titleMedium
                )
                MetaScore(score = it, modifier = Modifier.size(32.dp))
            }
            Text(
                text = stringResource(R.string.genre),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = game.genres.joinToString())
            Text(
                text = stringResource(R.string.developer),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = game.developers.joinToString())
            Text(
                text = stringResource(R.string.publisher),
                style = MaterialTheme.typography.titleMedium
            )
            if (game.publishers.isNotEmpty()) {
                Text(text = game.publishers.joinToString())
                Text(
                    text = stringResource(R.string.age_rating),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = game.ageRating
                    ?: stringResource(id = R.string.not_rated),
            )
            if (game.website.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.website),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier.clickable { uriHandler.openUri(game.website) },
                    text = game.website,
                    textDecoration = TextDecoration.Underline,
                )
            }
        }
    }
}