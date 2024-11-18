package com.example.videogamedbapp.data.remote.dtos

data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double,
)