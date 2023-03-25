package com.example.preecureapp.navigation

import com.example.preecureapp.R

sealed class AuthScreen(val route: String) {
    object SignInScreen: AuthScreen("signin_screen")
    object SignUpScreen: AuthScreen("signup_screen")
}

sealed class Profile(val route: String) {
    object AccountScreen: Profile("account_screen")
    object HealthScreen: Profile("health_screen")
    object OrdersScreen: Profile("orders_screen")
    object AddressesScreen: Profile("addresses_screen")
    object CardsScreen: Profile("cards_screen")
    object SettingsScreen: Profile("settings_screen")
    object HelpScreen: Profile("help_screen")
    object OffersScreen: Profile("offers_screen")
    object OptionsItemStyle : Profile("options_screen")
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}