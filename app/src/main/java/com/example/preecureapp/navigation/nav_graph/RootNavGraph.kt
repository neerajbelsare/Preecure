package com.example.preecureapp.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.preecureapp.navigation.AuthScreen
import com.example.preecureapp.screens.HomeScreen.HomeScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun isUserSignedIn(): Boolean {
    val currentUser = FirebaseAuth.getInstance().currentUser
    return currentUser != null
}

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val isSignedIn = isUserSignedIn()
    val startDest: String = if (isSignedIn) {
        Graph.HOME
    } else {
        Graph.AUTHENTICATION
    }
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startDest
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
    const val PROFILE = "profile_graph"
}

//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun NavGraph (){
//    val navController = rememberNavController()
//
//    val isSignedIn = isUserSignedIn()
//    val startDest: String = if (isSignedIn) {
//        Screens.MainScreen.route
//    } else {
//        Screens.SignInScreen.route
//    }
//
//    NavHost(
//        navController = navController,
//        startDestination = startDest)
//    {
//        composable(route = Screens.SignInScreen.route) {
//            SignInScreen(navController)
//        }
//        composable(route = Screens.SignUpScreen.route){
//            SignUpScreen(navController)
//        }
//        composable(route = Screens.MainScreen.route){
//            Home()
//        }
//    }
//}