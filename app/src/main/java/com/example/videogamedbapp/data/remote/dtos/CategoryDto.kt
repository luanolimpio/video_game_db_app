package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    val id: Int,
    val name: String,
    val slug: String,
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("image_background")
    val backgroundImage: String,
    val image: String?,
    val games: List<CategoryGame>,
)