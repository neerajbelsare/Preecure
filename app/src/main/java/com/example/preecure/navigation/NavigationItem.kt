package com.example.preecure.navigation

import com.example.preecure.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Chat : NavigationItem("chat", R.drawable.ic_chat, "Chat")
    object Explore : NavigationItem("explore", R.drawable.ic_explore, "Explore")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "Profile")
}