package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class Publisher(
    @SerializedName("games_count")
    val gamesCount: Int,
    val id: Int,
    @SerializedName("image_background")
    val imageBackground: String,
    val name: String,
    val slug: String
)