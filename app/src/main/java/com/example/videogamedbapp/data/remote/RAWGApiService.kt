package com.example.videogamedbapp.data.remote

import com.example.videogamedbapp.core.AppConstants
import com.example.videogamedbapp.data.remote.dtos.GameDetailsDto
import com.example.videogamedbapp.data.remote.dtos.GameDto
import com.example.videogamedbapp.data.remote.dtos.PageDataDto
import com.example.videogamedbapp.data.remote.dtos.CategoryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RAWGApiService {
    @GET(AppConstants.GAMES_ENDPOINT)
    suspend fun getAllGames(
        @Query("search") search: String?,
        @Query("platforms") platforms: String?,
        @Query("genres") genres: String?,
        @Query("publishers") publishers: String?,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int = AppConstants.PAGE_SIZE,
    ): PageDataDto<GameDto>

    @GET(AppConstants.BEST_GAMES_OF_THE_YEAR_ENDPOINT)
    suspend fun getBestGamesOfTheYear(
        @Query("ordering") ordering: String?,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int = AppConstants.PAGE_SIZE,
    ): PageDataDto<GameDto>

    @GET(AppConstants.RECENT_GAMES_ENDPOINT)
    suspend fun getRecentGames(
        @Query("ordering") ordering: String?,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int = AppConstants.PAGE_SIZE,
    ): PageDataDto<GameDto>

    @GET("${AppConstants.GAMES_ENDPOINT}/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int,
    ): GameDetailsDto

    @GET
    suspend fun getPlatforms(
        @Url path: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int = AppConstants.PAGE_SIZE,
    ): PageDataDto<CategoryDto>
}