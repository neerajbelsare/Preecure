package com.example.preecureapp

import android.graphics.drawable.Icon
import android.graphics.drawable.VectorDrawable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "Profile",
        icon = Icons.Default.Person
    )

    object Explore : BottomBarScreen(
        route = "Explore",
        icon = Icons.Default.Explore
    )

    object Chat : BottomBarScreen(
        route = "Chat",
        icon = Icons.Default.Chat
    )
}
