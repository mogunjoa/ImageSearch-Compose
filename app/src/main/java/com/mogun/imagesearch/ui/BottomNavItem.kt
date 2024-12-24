package com.mogun.imagesearch.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
) {
    object Search : BottomNavItem(
        title = "Search",
        route = "search",
        icon = Icons.Default.Search,
    )
    object Favorite : BottomNavItem(
        title = "Saved",
        route = "saved",
        icon = Icons.Default.Favorite,
    )
}