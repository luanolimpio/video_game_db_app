package com.example.videogamedbapp.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.videogamedbapp.domain.models.GameQueries
import com.example.videogamedbapp.domain.usecases.GetAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getAllGamesUseCase: GetAllGamesUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.QueryChanged -> {
                _uiState.value = _uiState.value.copy(query = event.text)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val games = getAllGamesUseCase(GameQueries(search = _uiState.value.query)).cachedIn(viewModelScope)
        _uiState.value = _uiState.value.copy(games = games)
    }
}