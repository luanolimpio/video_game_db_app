package com.example.videogamedbapp.data.remote.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.videogamedbapp.core.constants.AppConstants
import com.example.videogamedbapp.core.enums.CategoryTypeEnum
import com.example.videogamedbapp.data.remote.RAWGApiService
import com.example.videogamedbapp.data.remote.dtos.CategoryDto

class PlatformsPagingSource(
    private val apiService: RAWGApiService,
    private val categoryType: CategoryTypeEnum
) :
    PagingSource<Int, CategoryDto>() {
    override fun getRefreshKey(state: PagingState<Int, CategoryDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoryDto> {
        return try {
            val page = params.key ?: AppConstants.STARTING_PAGE_INDEX
            val response = apiService.getPlatforms(page = page, path = categoryType.value)
            val platforms = response.results
            LoadResult.Page(
                data = platforms,
                prevKey = if (page == AppConstants.STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (platforms.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}