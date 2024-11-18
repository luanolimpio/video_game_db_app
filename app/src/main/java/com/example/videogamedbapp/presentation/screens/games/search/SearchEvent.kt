package com.example.videogamedbapp.presentation.screens.games.search

sealed class SearchEvent {
    data class QueryChanged(val text: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}