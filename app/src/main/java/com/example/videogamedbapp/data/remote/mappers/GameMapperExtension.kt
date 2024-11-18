package com.example.videogamedbapp.data.remote.mappers

import com.example.videogamedbapp.R
import com.example.videogamedbapp.data.remote.dtos.GameDetailsDto
import com.example.videogamedbapp.data.remote.dtos.GameDto
import com.example.videogamedbapp.data.remote.dtos.PlatformX
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.domain.models.GameDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        slug = slug,
        backgroundImage = backgroundImage,
        parentPlatformIcons = parentPlatforms.map { it.platform.iconResource },
        metacritic = metacritic,
    )
}

fun GameDetailsDto.toGameDetails(): GameDetails {
    return GameDetails(
        id = id,
        slug = slug,
        name = name,
        description = descriptionRaw,
        metacritic = metacritic,
        released = formattedReleased,
        backgroundImage = backgroundImage,
        website = website,
        ratings = ratings,
        playtime = playtime,
        parentPlatformIcons = parentPlatforms.map { it.platform.iconResource },
        platforms = platforms.map { it.platform.name },
        developers = developers.map { it.name },
        genres = genres.map { it.name },
        publishers = publishers.map { it.name },
        ageRating = esrbRating?.name,
    )
}

private val GameDetailsDto.formattedReleased: String?
    get() {
        return released?.let {
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = LocalDate.parse(released, dateFormatter)
            DateTimeFormatter.ofPattern("MMMM dd, yyyy").format(formattedDate)
        }
    }

private val PlatformX.iconResource: Int?
    get() = when (id) {
        1 -> R.drawable.ic_pc

        2 -> R.drawable.ic_playstation

        3 -> R.drawable.ic_xbox

        5 -> R.drawable.ic_mac

        7 -> R.drawable.ic_nintendo

        else -> null
    }