package com.example.videogamedbapp.domain.usecases

import androidx.paging.PagingData
import com.example.videogamedbapp.core.enums.GameOrderingTypeEnum
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.domain.repositories.RAWGRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentGamesUseCase @Inject constructor(private val repository: RAWGRepository) {
    operator fun invoke() : Flow<PagingData<Game>> {
        return repository.getRecentGames(ordering = GameOrderingTypeEnum.ADDED.reverse())
    }
}