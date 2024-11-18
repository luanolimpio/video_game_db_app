package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class PlatformXXX(
    @SerializedName("games_count")
    val gamesCount: Int,
    val id: Int,
    val image: Any,
    @SerializedName("image_background")
    val imageBackground: String,
    val name: String,
    val slug: String,
)