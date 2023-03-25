package com.example.preecureapp.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.preecureapp.BottomBarScreen
import com.example.preecureapp.navigation.nav_graph.HomeNavGraph

//@Composable
//fun Home(navController: NavController) {
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController) },
//        content = { padding ->
//            Box(modifier = Modifier
//                .padding(padding)
//                ) {
//                Navigation(navController = navController)
//            }
//        },
//    )
//}
//
//@Composable
//fun Navigation(navController: NavHostController) {
//    NavHost(navController, startDestination = NavigationItem.Home.route) {
//        composable(NavigationItem.Home.route) {
//            HomeScreen()
//        }
//        composable(NavigationItem.Chat.route) {
//            ChatScreen()
//        }
//        composable(NavigationItem.Explore.route) {
//            ExploreScreen()
//        }
//        composable(NavigationItem.Profile.route) {
//            ProfileScreen()
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val items = listOf(
//        NavigationItem.Home,
//        NavigationItem.Chat,
//        NavigationItem.Explore,
//        NavigationItem.Profile
//    )
//    BottomNavigation(
//        modifier = Modifier
//            .padding(bottom = 10.dp, start = 25.dp, end = 25.dp)
//            .clip(RoundedCornerShape(30.dp))
//            .background(Color.White),
//        contentColor = Color.Black
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//        items.forEach { item ->
//            BottomNavigationItem(
//                modifier = Modifier
//                    .background(Color.White)
//                    .clip(RoundedCornerShape(30.dp)),
//                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
//                label = { Text(text = item.title) },
//                selectedContentColor = Color.Black,
//                unselectedContentColor = Color.Gray.copy(0.4f),
//                alwaysShowLabel = true,
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            modifier = Modifier
                .padding(bottom = 10.dp, start = 25.dp, end = 25.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White),
            contentColor = Color.Black
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(30.dp)),
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}