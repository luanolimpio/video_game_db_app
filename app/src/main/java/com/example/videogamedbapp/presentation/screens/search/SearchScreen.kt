package com.example.videogamedbapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videogamedbapp.R
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.presentation.components.CustomTopAppBar
import com.example.videogamedbapp.presentation.components.EmptyState
import com.example.videogamedbapp.presentation.components.HandlePagingResult
import com.example.videogamedbapp.presentation.components.ListShimmer
import com.example.videogamedbapp.presentation.components.SearchCard
import com.example.videogamedbapp.presentation.components.SearchTextField
import com.example.videogamedbapp.presentation.components.TopAppBarIcon

@Composable
fun SearchScreen(
    state: SearchUiState,
    onEvent: (SearchEvent) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToDetails: (Game) -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.search),
                navigationIcon = TopAppBarIcon(
                    icon = Icons.AutoMirrored.Default.ArrowBack,
                    onClick = onNavigateBack
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SearchTextField(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                value = state.query,
                onValueChange = { onEvent(SearchEvent.QueryChanged(it)) },
                onSearch = { onEvent(SearchEvent.SearchNews) },
            )
            if (state.games != null) {
                val pagingItems = state.games.collectAsLazyPagingItems()
                HandlePagingResult(
                    items = pagingItems,
                    loadingContent = {
                        ListShimmer()
                    },
                    successContent = {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(count = pagingItems.itemCount) { index ->
                                pagingItems[index]?.let {
                                    SearchCard(game = it, onClick = { onNavigateToDetails(it) })
                                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                                }
                            }
                        }
                    }
                )
            } else {
                EmptyState(
                    title = stringResource(R.string.looking_for_a_game),
                    description = stringResource(R.string.start_by_typing_its_name)
                )
            }
        }
    }
}