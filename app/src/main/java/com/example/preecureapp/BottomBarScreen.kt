package com.example.preecureapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "Home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "Profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Explore : BottomBarScreen(
        route = "Explore",
        title = "Explore",
        icon = Icons.Default.Explore
    )

    object Chat : BottomBarScreen(
        route = "Chat",
        title = "Chat",
        icon = Icons.Default.Chat
    )
}
