package com.example.preecure.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.preecure.screens.SigninScreen.SignInScreen
import com.example.preecure.screens.SignupScreen.SignUpScreen
import androidx.compose.animation.*
import com.example.preecure.screens.HomeScreen.AccountScreen
import com.example.preecure.screens.HomeScreen.Home
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.firebase.auth.FirebaseAuth

@Composable
fun isUserSignedIn(): Boolean {
    val currentUser = FirebaseAuth.getInstance().currentUser
    return currentUser != null
}

//@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph (navController: NavHostController){
    val isSignedIn = isUserSignedIn()
    lateinit var startDest: String
    if (isSignedIn) {
        startDest = Screens.Home.route
    } else {
        startDest = Screens.Signin.route
    }

    NavHost(
        navController = navController,
        startDestination = startDest)
    {
        composable(route = Screens.Signin.route){
            SignInScreen(navController)
        }
        composable(route = Screens.Signup.route){
            SignUpScreen(navController)
        }
        composable(route = Screens.Home.route){
            Home(navController)
        }
    }
}