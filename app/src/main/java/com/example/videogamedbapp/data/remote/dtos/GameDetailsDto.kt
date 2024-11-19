package com.example.videogamedbapp.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class GameDetailsDto(
    val id: Int,
    val slug: String,
    val name: String,
    @SerializedName("name_original")
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    @SerializedName("metacritic_platforms")
    val metacriticPlatforms: List<MetacriticPlatform>,
    val released: String?,
    val tba: Boolean,
    val updated: String,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String,
    val website: String,
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Int,
    val ratings: List<Rating>,
    val added: Int,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,
    val playtime: Int,
    @SerializedName("screenshots_count")
    val screenshotsCount: Int,
    @SerializedName("movies_count")
    val moviesCount: Int,
    @SerializedName("creators_count")
    val creatorsCount: Int,
    @SerializedName("achievements_count")
    val achievementsCount: Int,
    @SerializedName("parent_achievements_count")
    val parentAchievementsCount: Int,
    @SerializedName("reddit_url")
    val redditUrl: String,
    @SerializedName("reddit_name")
    val redditName: String,
    @SerializedName("reddit_description")
    val redditDescription: String,
    @SerializedName("reddit_logo")
    val redditLogo: String,
    @SerializedName("reddit_count")
    val redditCount: Int,
    @SerializedName("twitch_count")
    val twitchCount: Int,
    @SerializedName("youtube_count")
    val youtubeCount: Int,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @SerializedName("alternative_names")
    val alternativeNames: List<String>,
    @SerializedName("metacritic_url")
    val metacriticUrl: String,
    @SerializedName("parents_count")
    val parentsCount: Int,
    @SerializedName("additions_count")
    val additionsCount: Int,
    @SerializedName("game_series_count")
    val gameSeriesCount: Int,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("saturated_color")
    val saturatedColor: String,
    @SerializedName("dominant_color")
    val dominantColor: String,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>,
    val platforms: List<PlatformXX>,
    val stores: List<StoreXX>,
    val developers: List<Developer>,
    val genres: List<Genre>,
    val tags: List<Tag>,
    val publishers: List<Publisher>,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerializedName("description_raw")
    val descriptionRaw: String,
)