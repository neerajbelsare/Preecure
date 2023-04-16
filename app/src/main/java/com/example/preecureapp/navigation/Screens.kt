package com.example.preecureapp.navigation

import com.example.preecureapp.R
import java.text.Normalizer

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
}

sealed class FormScreen(val route: String) {
    object Doctor : FormScreen(route = "DOCTOR")
    object Lab : FormScreen(route = "LAB")
    object Pharmacy : FormScreen(route = "PHARMACY")
}

sealed class DoctorScreen(val route: String) {
    object DoctorForm2 : DoctorScreen(route = "DOCTOR2")
    object DoctorConfirm : FormScreen(route = "CONFIRM")
}