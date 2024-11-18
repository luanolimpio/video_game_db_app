package com.example.videogamedbapp.domain.usecases

import androidx.paging.PagingData
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.domain.models.GameQueries
import com.example.videogamedbapp.domain.repositories.RAWGRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGamesUseCase @Inject constructor(private val repository: RAWGRepository) {
    operator fun invoke(queries: GameQueries? = null): Flow<PagingData<Game>> {
        return repository.getAllGames(queries)
    }
}