package com.example.videogamedbapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videogamedbapp.R
import com.example.videogamedbapp.domain.models.Game
import com.example.videogamedbapp.presentation.components.HandlePagingResult
import com.example.videogamedbapp.presentation.components.ListShimmer
import com.example.videogamedbapp.presentation.components.SearchCard
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    state: SearchUiState,
    drawerState: DrawerState,
    onEvent: (SearchEvent) -> Unit,
    onNavigateToDetails: (Game) -> Unit,
) {
    Scaffold(
        topBar = {
            SearchTopAppBar(
                value = state.query,
                onValueChange = { onEvent(SearchEvent.QueryChanged(it)) },
                onSearch = { onEvent(SearchEvent.SearchNews) },
                drawerState = drawerState
            )
        },
    ) { innerPadding ->
        state.games?.let { games ->
            val pagingItems = games.collectAsLazyPagingItems()
            HandlePagingResult(
                items = pagingItems,
                loadingContent = {
                    ListShimmer(modifier = Modifier.padding(innerPadding))
                },
                successContent = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
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
        }
    }
}

@Composable
private fun SearchTopAppBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    drawerState: DrawerState
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxSize(),
            color = Color.Transparent,
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester),
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                        text = stringResource(R.string.search_for_games),
                        textAlign = TextAlign.Center
                    )
                },
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                            focusManager.clearFocus()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { onValueChange("") }, enabled = value.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (value.isNotEmpty()) onSearch()
                    }
                )
            )
        }
    }
}