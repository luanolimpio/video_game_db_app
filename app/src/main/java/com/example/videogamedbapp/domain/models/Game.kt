package com.example.videogamedbapp.domain.models

data class Game(
    val id: Int,
    val name: String,
    val slug: String,
    val backgroundImage: String,
    val parentPlatformIcons: List<Int?>,
    val metacritic: Int?
)
