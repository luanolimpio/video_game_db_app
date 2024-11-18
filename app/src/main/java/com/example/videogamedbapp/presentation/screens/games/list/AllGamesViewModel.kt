package com.example.videogamedbapp.presentation.screens.games.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.usecases.GetAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllGamesViewModel @Inject constructor(getAllGamesUseCase: GetAllGamesUseCase) :
    ViewModel() {
    val games = getAllGamesUseCase().cachedIn(viewModelScope)
}