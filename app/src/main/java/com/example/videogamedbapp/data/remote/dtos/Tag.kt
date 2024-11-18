package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class Tag(
    val id: Int,
    val name: String,
    val slug: String,
    val language: String,
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("image_background")
    val imageBackground: String,
)