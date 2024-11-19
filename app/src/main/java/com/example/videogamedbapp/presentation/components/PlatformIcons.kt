package com.example.videogamedbapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun PlatformIcons(
    parentPlatformIcons: List<Int?>,
    color: Color,
    spacing: Dp,
    modifier: Modifier = Modifier,
    additionalContent: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        parentPlatformIcons.forEach { item ->
            item?.let {
                Icon(
                    modifier = modifier,
                    tint = color,
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
        }
        if (additionalContent != null) {
            Spacer(modifier = Modifier.weight(1f))
            additionalContent()
        }
    }
}