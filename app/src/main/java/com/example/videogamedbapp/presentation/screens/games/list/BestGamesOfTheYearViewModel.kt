package com.example.videogamedbapp.presentation.screens.games.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.usecases.GetBestGamesOfTheYearUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BestGamesOfTheYearViewModel @Inject constructor(getBestGamesOfTheYearUseCase: GetBestGamesOfTheYearUseCase) :
    ViewModel() {
    val games = getBestGamesOfTheYearUseCase().cachedIn(viewModelScope)
}