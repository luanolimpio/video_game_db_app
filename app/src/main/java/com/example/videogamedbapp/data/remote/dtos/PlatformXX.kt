package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class PlatformXX(
    val platform: PlatformXXX,
    @SerializedName("released_at")
    val releasedAt: String,
    val requirements: Requirements
)