package com.example.videogamedbapp.presentation.screens.games.details

import com.example.videogamedbapp.domain.models.GameDetails

sealed class GameDetailsUiState {
    data object Initial : GameDetailsUiState()
    data object Loading : GameDetailsUiState()
    data class Success(val game: GameDetails) : GameDetailsUiState()
    data class Error(val message: String) : GameDetailsUiState()
}
