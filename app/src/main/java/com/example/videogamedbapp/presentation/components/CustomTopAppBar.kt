package com.example.videogamedbapp.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

data class TopAppBarIcon(val icon: ImageVector, val onClick: () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: TopAppBarIcon,
    actions: List<TopAppBarIcon>? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        navigationIcon = {
            IconButton(onClick = navigationIcon.onClick) {
                Icon(
                    imageVector = navigationIcon.icon,
                    contentDescription = null
                )
            }
        },
        actions = {
            actions?.let { action ->
                action.forEach {
                    IconButton(
                        onClick = it.onClick,
                    ) {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    )
}