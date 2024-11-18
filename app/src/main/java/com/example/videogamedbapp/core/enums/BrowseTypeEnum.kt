package com.example.videogamedbapp.core.enums

import com.example.videogamedbapp.core.constants.AppConstants

enum class CategoryTypeEnum(val value: String) {
    PLATFORMS(AppConstants.PLATFORMS_ENDPOINT),
    GENRES(AppConstants.GENRES_ENDPOINT),
    PUBLISHERS(AppConstants.PUBLISHERS_ENDPOINT),
}