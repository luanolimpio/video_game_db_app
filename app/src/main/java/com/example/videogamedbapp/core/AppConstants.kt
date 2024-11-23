package com.example.videogamedbapp.core

import com.example.videogamedbapp.BuildConfig

object AppConstants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.rawg.io/api/"
    const val GAMES_ENDPOINT = "games"
    const val BEST_GAMES_OF_THE_YEAR_ENDPOINT = "games/lists/greatest"
    const val RECENT_GAMES_ENDPOINT = "games/lists/recent-games-past"
    const val PLATFORMS_ENDPOINT = "platforms"
    const val GENRES_ENDPOINT = "genres"
    const val PUBLISHERS_ENDPOINT = "publishers"
    const val STARTING_PAGE_INDEX = 1
    const val PAGE_SIZE = 20
    const val GAME_DETAILS_ARGUMENT_KEY = "id"
}