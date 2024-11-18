package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    @SerializedName("background_image")
    val backgroundImage: String,
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Int,
    val ratings: List<Rating>,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int,
    val added: Int,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,
    val metacritic: Int?,
    val playtime: Int,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    val updated: String,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("saturated_color")
    val saturatedColor: String,
    @SerializedName("dominant_color")
    val dominantColor: String,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>,
    val genres: List<Genre>,
    val stores: List<Store>,
    val tags: List<Tag>,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating,
)