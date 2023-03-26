package com.example.preecureapp.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.preecure.screens.HomeScreen.ProfileScreen
import com.example.preecureapp.BottomBarScreen
import com.example.preecureapp.navigation.FormScreen
import com.example.preecureapp.navigation.Profile
import com.example.preecureapp.screens.AccountNavScreens.*
import com.example.preecureapp.screens.ScreenContent
import java.text.Normalizer

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            ScreenContent(
                name = BottomBarScreen.Home.route,
                onClick = {
                    navController.navigate(Graph.DETAILS)
                }
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Explore.route) {
            ScreenContent(
                name = BottomBarScreen.Explore.route,
                onClick = {

                }
            )
        }
        composable(route = BottomBarScreen.Chat.route) {
            ScreenContent(
                name = BottomBarScreen.Chat.route,
                onClick = {

                }
            )
        }
        profileNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = Profile.AccountScreen.route
    ) {
        composable(route = Profile.AccountScreen.route) {
            AccountScreen(navController = navController)
        }
        composable(route = Profile.HealthScreen.route) {
            HealthScreen(navController = navController)
        }
        composable(route = Profile.OrdersScreen.route) {
            OrdersScreen(navController = navController)
        }
        composable(route = Profile.AddressesScreen.route) {
            AddressesScreen(navController = navController)
        }
        composable(route = Profile.CardsScreen.route) {
            CardsScreen(navController = navController)
        }
        composable(route = Profile.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Profile.HelpScreen.route) {
            HelpScreen(navController = navController)
        }
        composable(route = Profile.OffersScreen.route) {
            OffersScreen(navController = navController)
        }
        formNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.formNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.FORMS,
        startDestination = FormScreen.Doctor.route
    ) {
        composable(route = FormScreen.Doctor.route) {
            DoctorForm(navController = navController)
        }
        composable(route = FormScreen.Lab.route) {
            LabForm()
        }
        composable(route = FormScreen.Pharmacy.route) {
            PharmacyForm()
        }
    }
}