package com.example.videogamedbapp.data.remote.dtos

data class PageDataDto<T>(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<T>,
)
