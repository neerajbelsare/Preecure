package com.example.preecureapp.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.preecure.screens.HomeScreen.ProfileScreen
import com.example.preecureapp.BottomBarScreen
import com.example.preecureapp.navigation.DetailsScreen
import com.example.preecureapp.navigation.Profile
import com.example.preecureapp.screens.AccountDetailsScreen.AccountScreen
import com.example.preecureapp.screens.ScreenContent

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
        detailsNavGraph(navController = navController)
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
    }
}
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}