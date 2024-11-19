package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.videogamedbapp.R
import com.example.videogamedbapp.core.extensions.hasCurrentRoute
import com.example.videogamedbapp.core.routes.Routes
import com.example.videogamedbapp.ui.theme.Shapes
import kotlinx.coroutines.launch

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: Routes
)

@Composable
fun CustomNavigationDrawer(
    items: List<NavigationItem>,
    navController: NavController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(drawerShape = RectangleShape) {
                DrawerHeader()
                Spacer(modifier = Modifier.height(8.dp))
                DrawerBody(
                    items = items,
                    navController = navController,
                    drawerState = drawerState
                )
            }
        },
        drawerState = drawerState,
        content = content,
    )
}

@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.games),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                Color.Gray.copy(0.8f),
                BlendMode.Modulate
            ),
        )
        Text(
            text = "${stringResource(R.string.thousands_of_games)}\n${stringResource(R.string.start_exploring_now)}",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun DrawerBody(
    items: List<NavigationItem>,
    navController: NavController,
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            NavigationDrawerItem(
                selected = backStackEntry?.destination.hasCurrentRoute(item.route::class),
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                shape = Shapes.medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomNavigationDrawerPreview() {
    CustomNavigationDrawer(
        items = listOf(
            NavigationItem(
                title = stringResource(R.string.search),
                route = Routes.Search,
                icon = Icons.Default.Search,
            ),
            NavigationItem(
                title = stringResource(R.string.all_games),
                route = Routes.AllGames,
                icon = Icons.Default.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.new_releases),
                route = Routes.RecentGames,
                icon = Icons.Default.Star,
            ),
            NavigationItem(
                title = stringResource(R.string.best_of_the_year),
                route = Routes.BestGamesOfTheYear,
                icon = Icons.Default.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.platforms),
                route = Routes.AllGames,
                icon = Icons.Default.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.genres),
                route = Routes.RecentGames,
                icon = Icons.Default.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.publishers),
                route = Routes.BestGamesOfTheYear,
                icon = Icons.AutoMirrored.Filled.Send,
            ),
        ),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
        navController = rememberNavController(),
        content = {}
    )
}

