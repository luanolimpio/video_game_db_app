package com.example.videogamedbapp.domain.repositories

import androidx.paging.PagingData
import com.example.videogamedbapp.core.Resource
import com.example.videogamedbapp.core.enums.CategoryTypeEnum
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.domain.models.GameDetails
import com.example.videogamedbapp.domain.models.GameQueries
import com.example.videogamedbapp.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface RAWGRepository {
    fun getAllGames(queries: GameQueries?): Flow<PagingData<Game>>
    fun getBestGamesOfTheYear(ordering: String): Flow<PagingData<Game>>
    fun getRecentGames(ordering: String): Flow<PagingData<Game>>
    suspend fun getGameDetails(id: Int): Resource<GameDetails>
    fun getCategories(type: CategoryTypeEnum): Flow<PagingData<Category>>
}