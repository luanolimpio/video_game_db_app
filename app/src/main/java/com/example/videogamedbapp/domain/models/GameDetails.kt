package com.example.videogamedbapp.domain.models

import com.example.videogamedbapp.data.remote.dtos.Rating

data class GameDetails(
    val id: Int,
    val slug: String,
    val name: String,
    val description: String,
    val metacritic: Int?,
    val released: String?,
    val backgroundImage: String,
    val website: String,
    val ratings: List<Rating>,
    val playtime: Int,
    val parentPlatformIcons: List<Int?>,
    val platforms: List<String>,
    val developers: List<String>,
    val genres: List<String>,
    val publishers: List<String>,
    val ageRating: String?
)
