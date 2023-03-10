package com.example.preecure.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.preecure.screens.SigninScreen.SignInScreen
import com.example.preecure.screens.SignupScreen.SignUpScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Signin.route)
    {
        composable(route = Screens.Signin.route){
            SignInScreen(navController)
        }
        composable(route = Screens.Signup.route){
            SignUpScreen(navController)
        }
    }
}