package com.example.videogamedbapp.core.enums

enum class GameOrderingTypeEnum(val value: String) {
    NAME("name"),
    RELEASED("released"),
    ADDED("added"),
    CREATED("created"),
    UPDATED("updated"),
    RATING("rating"),
    META_CRITIC("metacritic");

    fun reverse(): String{
        return "-${value}"
    }
}