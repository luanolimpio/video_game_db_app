package com.example.videogamedbapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videogamedbapp.R
import com.example.videogamedbapp.core.routes.CustomNavType.Companion.getCustomNavTypeMap
import com.example.videogamedbapp.core.routes.Routes
import com.example.videogamedbapp.domain.models.GameQueries
import com.example.videogamedbapp.presentation.components.CustomNavigationDrawer
import com.example.videogamedbapp.presentation.components.CustomTopAppBar
import com.example.videogamedbapp.presentation.components.NavigationItem
import com.example.videogamedbapp.presentation.components.TopAppBarIcon
import com.example.videogamedbapp.presentation.screens.games.list.GamesScreen
import com.example.videogamedbapp.presentation.screens.games.details.GameDetailsScreen
import com.example.videogamedbapp.presentation.screens.games.details.GameDetailsViewModel
import com.example.videogamedbapp.presentation.screens.search.SearchScreen
import com.example.videogamedbapp.presentation.screens.search.SearchViewModel
import com.example.videogamedbapp.presentation.screens.categories.CategoriesScreen
import com.example.videogamedbapp.presentation.screens.categories.CategoryGamesViewModel
import com.example.videogamedbapp.presentation.screens.categories.CategoriesViewModel
import com.example.videogamedbapp.presentation.screens.games.list.GamesViewModel
import kotlinx.coroutines.launch

@Composable
fun MainNavigator() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    CustomNavigationDrawer(
        items = listOf(
            NavigationItem(
                title = stringResource(R.string.all_games),
                route = Routes.AllGames
            ),
            NavigationItem(
                title = stringResource(R.string.new_releases),
                route = Routes.RecentGames
            ),
            NavigationItem(
                title = stringResource(R.string.best_of_the_year),
                route = Routes.BestGamesOfTheYear
            ),
            NavigationItem(
                title = stringResource(R.string.platforms),
                route = Routes.Platforms
            ),
            NavigationItem(
                title = stringResource(R.string.genres),
                route = Routes.Genres
            ),
            NavigationItem(
                title = stringResource(R.string.publishers),
                route = Routes.Publishers
            ),
        ),
        navController = navController,
        drawerState = drawerState
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.AllGames,
        ) {
            composable<Routes.AllGames> {
                val viewModel = hiltViewModel<GamesViewModel>()
                val games = viewModel.allGames.collectAsLazyPagingItems()
                val scope = rememberCoroutineScope()
                GamesScreen(
                    topBar = {
                        CustomTopAppBar(
                            title = stringResource(R.string.all_games),
                            navigationIcon = TopAppBarIcon(
                                icon = Icons.Default.Menu,
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            ),
                            actions = listOf(
                                TopAppBarIcon(
                                    icon = Icons.Default.Search,
                                    onClick = {
                                        navController.navigate(Routes.Search)
                                    }
                                )
                            )
                        )
                    },
                    games = games,
                    onNavigateToDetails = { game ->
                        navController.navigate(Routes.GameDetails(game.id))
                    }
                )
            }

            composable<Routes.RecentGames> {
                val viewModel = hiltViewModel<GamesViewModel>()
                val games = viewModel.recentGames.collectAsLazyPagingItems()
                val scope = rememberCoroutineScope()
                GamesScreen(
                    topBar = {
                        CustomTopAppBar(
                            title = stringResource(R.string.new_releases),
                            navigationIcon = TopAppBarIcon(
                                icon = Icons.Default.Menu,
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            ),
                        )
                    },
                    games = games,
                    onNavigateToDetails = { game ->
                        navController.navigate(Routes.GameDetails(game.id))
                    }
                )
            }

            composable<Routes.BestGamesOfTheYear> {
                val viewModel = hiltViewModel<GamesViewModel>()
                val games = viewModel.bestGames.collectAsLazyPagingItems()
                val scope = rememberCoroutineScope()
                GamesScreen(
                    topBar = {
                        CustomTopAppBar(
                            title = stringResource(R.string.best_of_the_year),
                            navigationIcon = TopAppBarIcon(
                                icon = Icons.Default.Menu,
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            ),
                        )
                    },
                    games = games,
                    onNavigateToDetails = { game ->
                        navController.navigate(Routes.GameDetails(game.id))
                    }
                )
            }

            composable<Routes.Search> {
                val viewModel = hiltViewModel<SearchViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                SearchScreen(
                    state = uiState,
                    onEvent = viewModel::onEvent,
                    onNavigateToDetails = { game ->
                        navController.navigate(Routes.GameDetails(game.id))
                    },
                    onNavigateBack = { navController.popBackStack() },
                )
            }

            composable<Routes.GameDetails> { backStackEntry ->
                val viewModel = hiltViewModel<GameDetailsViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val id = backStackEntry.toRoute<Routes.GameDetails>().id
                GameDetailsScreen(
                    state = uiState,
                    onNavigateBack = { navController.popBackStack() },
                    onRefresh = { viewModel.getGamesDetails(id) }
                )
            }

            composable<Routes.Platforms> {
                val viewModel = hiltViewModel<CategoriesViewModel>()
                val platforms = viewModel.platforms.collectAsLazyPagingItems()
                val gamesForString = stringResource(R.string.games_for)
                CategoriesScreen(
                    title = stringResource(R.string.platforms),
                    categories = platforms,
                    drawerState = drawerState,
                    onNavigateToGames = { platform ->
                        navController.navigate(
                            Routes.CategoryGames(
                                title = "$gamesForString ${platform.name}",
                                queries = GameQueries(platforms = platform.id.toString())
                            )
                        )
                    }
                )
            }

            composable<Routes.Genres> {
                val viewModel = hiltViewModel<CategoriesViewModel>()
                val genres = viewModel.genres.collectAsLazyPagingItems()
                val gamesString = stringResource(R.string.games)
                CategoriesScreen(
                    title = stringResource(R.string.genres),
                    categories = genres,
                    drawerState = drawerState,
                    onNavigateToGames = { platform ->
                        navController.navigate(
                            Routes.CategoryGames(
                                title = "${platform.name} $gamesString",
                                queries = GameQueries(genres = platform.id.toString())
                            )
                        )
                    }
                )
            }

            composable<Routes.Publishers> {
                val viewModel = hiltViewModel<CategoriesViewModel>()
                val publishers = viewModel.publishers.collectAsLazyPagingItems()
                val publishedByString = stringResource(R.string.published_by)
                CategoriesScreen(
                    title = stringResource(R.string.publishers),
                    categories = publishers,
                    drawerState = drawerState,
                    onNavigateToGames = { platform ->
                        navController.navigate(
                            Routes.CategoryGames(
                                title = "$publishedByString ${platform.name}",
                                queries = GameQueries(publishers = platform.id.toString()),
                            )
                        )
                    }
                )
            }

            composable<Routes.CategoryGames>(typeMap = getCustomNavTypeMap(GameQueries.serializer())) { backStackEntry ->
                val viewModel = hiltViewModel<CategoryGamesViewModel>()
                val params = backStackEntry.toRoute<Routes.CategoryGames>()
                val games = viewModel.getGamesByQueries(params.queries).collectAsLazyPagingItems()
                GamesScreen(
                    topBar = {
                        CustomTopAppBar(
                            title = params.title,
                            navigationIcon = TopAppBarIcon(
                                icon = Icons.AutoMirrored.Default.ArrowBack,
                                onClick = { navController.popBackStack() }
                            )
                        )
                    },
                    games = games,
                    onNavigateToDetails = { game ->
                        navController.navigate(Routes.GameDetails(game.id))
                    }
                )
            }
        }
    }
}