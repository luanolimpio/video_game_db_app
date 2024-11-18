package com.example.videogamedbapp.presentation.screens.games.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.presentation.components.CustomLazyVerticalGrid
import com.example.videogamedbapp.presentation.components.HandlePagingResult
import com.example.videogamedbapp.presentation.components.VerticalGridShimmer
import com.example.videogamedbapp.presentation.components.GameCard

@Composable
fun GamesScreen(
    topBar: @Composable () -> Unit = {},
    games: LazyPagingItems<Game>,
    onNavigateToDetails: (Game) -> Unit
) {
    Scaffold(
        topBar = topBar
    ) { innerPadding ->
        HandlePagingResult(
            items = games,
            loadingContent = {
                VerticalGridShimmer(modifier = Modifier.padding(innerPadding))
            },
            successContent = {
                CustomLazyVerticalGrid(
                    count = games.itemCount,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) { index ->
                    games[index]?.let {
                        GameCard(game = it, onClick = { onNavigateToDetails(it) })
                    }
                }
            }
        )
    }
}