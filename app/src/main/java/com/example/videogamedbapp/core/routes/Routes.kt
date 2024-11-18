package com.example.videogamedbapp.core.routes

import com.example.videogamedbapp.domain.models.GameQueries
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object AllGames : Routes()

    @Serializable
    data object RecentGames : Routes()

    @Serializable
    data object BestGamesOfTheYear : Routes()

    @Serializable
    data object Search : Routes()

    @Serializable
    data class GameDetails(val id: Int) : Routes()

    @Serializable
    data object Platforms : Routes()

    @Serializable
    data object Genres : Routes()

    @Serializable
    data object Publishers : Routes()

    @Serializable
    data class CategoryGames(val title: String, val queries: GameQueries) : Routes()
}