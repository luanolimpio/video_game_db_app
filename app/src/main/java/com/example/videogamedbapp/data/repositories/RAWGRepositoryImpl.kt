package com.example.videogamedbapp.data.repositories

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.videogamedbapp.core.Resource
import com.example.videogamedbapp.core.constants.AppConstants
import com.example.videogamedbapp.core.enums.CategoryTypeEnum
import com.example.videogamedbapp.data.remote.RAWGApiService
import com.example.videogamedbapp.data.remote.mappers.toCategory
import com.example.videogamedbapp.data.remote.mappers.toGame
import com.example.videogamedbapp.data.remote.mappers.toGameDetails
import com.example.videogamedbapp.data.remote.sources.BestGamesOfTheYearPagingSource
import com.example.videogamedbapp.data.remote.sources.GamesPagingSource
import com.example.videogamedbapp.data.remote.sources.PlatformsPagingSource
import com.example.videogamedbapp.data.remote.sources.RecentGamesPagingSource
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.domain.models.GameDetails
import com.example.videogamedbapp.domain.models.GameQueries
import com.example.videogamedbapp.domain.models.Category
import com.example.videogamedbapp.domain.repositories.RAWGRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RAWGRepositoryImpl @Inject constructor(
    private val apiService: RAWGApiService,
    @ApplicationContext context: Context,
) :
    RAWGRepository, BaseRepository(context) {
    override fun getAllGames(queries: GameQueries?): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = AppConstants.PAGE_SIZE),
            pagingSourceFactory = {
                GamesPagingSource(
                    apiService = apiService,
                    queries = queries,
                )
            }
        ).flow.map { pagingData -> pagingData.map { it.toGame() } }
    }

    override fun getBestGamesOfTheYear(ordering: String): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = AppConstants.PAGE_SIZE),
            pagingSourceFactory = {
                BestGamesOfTheYearPagingSource(
                    apiService = apiService,
                    ordering = ordering
                )
            }
        ).flow.map { pagingData -> pagingData.map { it.toGame() } }
    }

    override fun getRecentGames(ordering: String): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = AppConstants.PAGE_SIZE),
            pagingSourceFactory = {
                RecentGamesPagingSource(
                    apiService = apiService,
                    ordering = ordering
                )
            }
        ).flow.map { pagingData -> pagingData.map { it.toGame() } }
    }

    override suspend fun getGameDetails(id: Int): Resource<GameDetails> {
        return handleApiCall { apiService.getGameDetails(id).toGameDetails() }
    }

    override fun getCategories(type: CategoryTypeEnum): Flow<PagingData<Category>> {
        return Pager(
            config = PagingConfig(pageSize = AppConstants.PAGE_SIZE),
            pagingSourceFactory = {
                PlatformsPagingSource(
                    apiService = apiService,
                    categoryType = type
                )
            }
        ).flow.map { pagingData -> pagingData.map { it.toCategory() } }
    }
}