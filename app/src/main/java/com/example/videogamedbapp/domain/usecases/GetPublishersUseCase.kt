package com.example.videogamedbapp.domain.usecases

import androidx.paging.PagingData
import com.example.videogamedbapp.core.enums.CategoryTypeEnum
import com.example.videogamedbapp.domain.models.Category
import com.example.videogamedbapp.domain.repositories.RAWGRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPublishersUseCase @Inject constructor(private val repository: RAWGRepository) {
    operator fun invoke() : Flow<PagingData<Category>> {
        return repository.getCategories(type = CategoryTypeEnum.PUBLISHERS)
    }
}