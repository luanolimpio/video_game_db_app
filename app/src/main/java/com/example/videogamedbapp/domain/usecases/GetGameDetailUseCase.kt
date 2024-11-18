package com.example.videogamedbapp.domain.usecases

import com.example.videogamedbapp.core.Resource
import com.example.videogamedbapp.domain.models.GameDetails
import com.example.videogamedbapp.domain.repositories.RAWGRepository
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(private val repository: RAWGRepository) {
    suspend operator fun invoke(id: Int): Resource<GameDetails> {
        return repository.getGameDetails(id)
    }
}