package com.example.preecureapp.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.preecureapp.navigation.AuthScreen
import com.example.preecureapp.screens.SigninScreen.SignInScreen
import com.example.preecureapp.screens.SignupScreen.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.SignInScreen.route
    ) {
        composable(route = AuthScreen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(route = AuthScreen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
    }
}