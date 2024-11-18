package com.example.videogamedbapp.presentation.screens.games.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.usecases.GetAllGamesUseCase
import com.example.videogamedbapp.domain.usecases.GetBestGamesOfTheYearUseCase
import com.example.videogamedbapp.domain.usecases.GetRecentGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    getAllGamesUseCase: GetAllGamesUseCase,
    getBestGamesOfTheYearUseCase: GetBestGamesOfTheYearUseCase,
    getRecentGamesUseCase: GetRecentGamesUseCase
) :
    ViewModel() {
    val allGames = getAllGamesUseCase().cachedIn(viewModelScope)
    val bestGames = getBestGamesOfTheYearUseCase().cachedIn(viewModelScope)
    val recentGames = getRecentGamesUseCase().cachedIn(viewModelScope)
}