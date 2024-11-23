package com.example.videogamedbapp.data.remote.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.videogamedbapp.core.AppConstants
import com.example.videogamedbapp.data.remote.RAWGApiService
import com.example.videogamedbapp.data.remote.dtos.GameDto
import com.example.videogamedbapp.domain.models.GameQueries

class GamesPagingSource(
    private val apiService: RAWGApiService,
    private val queries: GameQueries?
) :
    PagingSource<Int, GameDto>() {
    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        return try {
            val page = params.key ?: AppConstants.STARTING_PAGE_INDEX
            val response = apiService.getAllGames(
                search = queries?.search,
                platforms = queries?.platforms,
                genres = queries?.genres,
                publishers = queries?.publishers,
                page = page
            )
            val games = response.results
            LoadResult.Page(
                data = games,
                prevKey = if (page == AppConstants.STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (games.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}