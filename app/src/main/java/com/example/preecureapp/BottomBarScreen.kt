package com.example.preecureapp


sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "Home",
        icon = R.drawable.ic_home
    )

    object Profile : BottomBarScreen(
        route = "Profile",
        icon = R.drawable.ic_profile
    )

    object Explore : BottomBarScreen(
        route = "Explore",
        icon = R.drawable.ic_explore
    )

    object Chat : BottomBarScreen(
        route = "Chat",
        icon = R.drawable.ic_chat
    )
}
