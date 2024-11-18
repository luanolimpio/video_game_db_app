package com.example.videogamedbapp.data.remote.mappers

import com.example.videogamedbapp.data.remote.dtos.CategoryDto
import com.example.videogamedbapp.domain.models.Category

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        slug = slug,
        backgroundImage = backgroundImage,
        gamesCount = gamesCount,
    )
}