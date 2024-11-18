package com.example.videogamedbapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.videogamedbapp.core.extensions.message

@Composable
fun <T : Any> HandlePagingResult(
    items: LazyPagingItems<T>,
    loadingContent: @Composable () -> Unit,
    successContent: @Composable () -> Unit
) {
    items.loadState.apply {
        when {
            refresh is LoadState.Loading -> loadingContent()

            refresh is LoadState.Error -> {
                val error = refresh as LoadState.Error
                ErrorState(
                    message = stringResource(error.message),
                    onRefresh = { items.refresh() }
                )
            }

            items.itemCount == 0 -> {
                EmptyState(onRefresh = { items.refresh() })
            }

            else -> successContent()
        }
    }
}