package com.example.videogamedbapp.presentation.screens.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.videogamedbapp.domain.models.Category
import com.example.videogamedbapp.presentation.components.CustomLazyVerticalGrid
import com.example.videogamedbapp.presentation.components.HandlePagingResult
import com.example.videogamedbapp.presentation.components.VerticalGridShimmer
import com.example.videogamedbapp.presentation.components.CategoryCard
import com.example.videogamedbapp.presentation.components.CustomTopAppBar
import com.example.videogamedbapp.presentation.components.TopAppBarIcon
import kotlinx.coroutines.launch

@Composable
fun CategoriesScreen(
    title: String,
    drawerState: DrawerState,
    categories: LazyPagingItems<Category>,
    onNavigateToGames: (Category) -> Unit
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                navigationIcon = TopAppBarIcon(
                    icon = Icons.Default.Menu,
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                ),
            )
        }
    ) { innerPadding ->
        HandlePagingResult(
            items = categories,
            loadingContent = {
                VerticalGridShimmer(modifier = Modifier.padding(innerPadding))
            },
            successContent = {
                CustomLazyVerticalGrid(
                    count = categories.itemCount,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) { index ->
                    categories[index]?.let {
                        CategoryCard(category = it, onClick = { onNavigateToGames(it) })
                    }
                }
            }
        )
    }
}