package com.example.videogamedbapp.presentation.screens.search

import androidx.paging.PagingData
import com.example.videogamedbapp.domain.models.Game
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val query: String = "",
    val games: Flow<PagingData<Game>>? = null
)
