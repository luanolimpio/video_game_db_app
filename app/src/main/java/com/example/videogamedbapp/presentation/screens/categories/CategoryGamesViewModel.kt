package com.example.videogamedbapp.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.domain.models.GameQueries
import com.example.videogamedbapp.domain.usecases.GetAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoryGamesViewModel @Inject constructor(
    private val getAllGamesUseCase: GetAllGamesUseCase
) :
    ViewModel() {

    fun getGamesByQueries(queries: GameQueries): Flow<PagingData<Game>> =
        getAllGamesUseCase(queries).cachedIn(viewModelScope)
}