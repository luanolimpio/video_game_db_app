package com.example.videogamedbapp.presentation.screens.games.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videogamedbapp.core.Resource
import com.example.videogamedbapp.core.AppConstants
import com.example.videogamedbapp.domain.usecases.GetGameDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailUseCase: GetGameDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<GameDetailsUiState>(GameDetailsUiState.Initial)
    val uiState = _uiState.asStateFlow()

    init {
        val id = savedStateHandle.get<Int>(AppConstants.GAME_DETAILS_ARGUMENT_KEY)
        id?.let {
            getGamesDetails(it)
        }
    }

    fun getGamesDetails(id: Int) {
        viewModelScope.launch {
            _uiState.value = GameDetailsUiState.Loading
            when (val result = getGameDetailUseCase(id)) {
                is Resource.Success -> {
                    _uiState.value = GameDetailsUiState.Success(game = result.data)
                }

                is Resource.Error -> {
                    _uiState.value = GameDetailsUiState.Error(message = result.message)
                }
            }
        }
    }
}
