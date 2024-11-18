package com.example.videogamedbapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GameQueries(
    val search: String? = null,
    val platforms: String? = null,
    val genres: String? = null,
    val publishers: String? = null,
) : Parcelable