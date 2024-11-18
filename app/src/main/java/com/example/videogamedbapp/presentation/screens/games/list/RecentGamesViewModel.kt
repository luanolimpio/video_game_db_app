package com.example.videogamedbapp.presentation.screens.games.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.usecases.GetRecentGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecentGamesViewModel @Inject constructor(getRecentGamesUseCase: GetRecentGamesUseCase) :
    ViewModel() {
    val games = getRecentGamesUseCase().cachedIn(viewModelScope)
}