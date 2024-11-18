package com.example.videogamedbapp.domain.models

data class Category(
    val id: Int,
    val name: String,
    val slug: String,
    val backgroundImage: String,
    val gamesCount: Int,
)
