package com.example.preecure.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.preecureapp.navigation.Profile
import com.example.preecureapp.navigation.nav_graph.Graph
import com.example.preecureapp.screens.HomeScreen.ProfileViewModel
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun ChatScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun ExploreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Books View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        ProfileEcommerce(navController = navController)
    }
}

private val optionsList: ArrayList<OptionsData> = ArrayList()

@Composable
fun ProfileEcommerce(navController: NavController, context: Context = LocalContext.current.applicationContext) {
    // This indicates if the optionsList has data or not
    // Initially, the list is empty. So, its value is false.
    var listPrepared by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            optionsList.clear()

            // Add the data to optionsList
            prepareOptionsData()

            listPrepared = true
        }
    }

    if (listPrepared) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            item {
                // User's image, name, email and edit button
                UserDetails(context = context)
            }

            // Show the options
            items(optionsList) {
                    item ->
                OptionsItemStyle(item = item, context = context, navController = navController)
            }

        }
    }
}

@Composable
private fun UserDetails(context: Context, profileViewModel: ProfileViewModel = viewModel()) {
    val user = profileViewModel.user.value
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(110.dp)
            .background(Color.White, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (user != null) {
            // User's image
            if (profileViewModel.isLoading) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .padding(start = 20.dp)
                        .clip(shape = CircleShape)
                        .shimmer()
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(72.dp)
                        .padding(start = 20.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = com.example.preecureapp.R.drawable.victoria),
                    contentDescription = "Your Image"
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(weight = 3f, fill = false)
                        .padding(start = 16.dp)
                ) {

                    // User's name
                    if (profileViewModel.isLoading) {
                        Text(
                            text = "Shimmering Text",
                            modifier = Modifier.shimmer()
                        )
                    } else {
                        Text(
                            text = user.name,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        com.example.preecureapp.R.font.roboto_bold,
                                        FontWeight.Bold
                                    )
                                ),
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    // User's email
                    if (profileViewModel.isLoading) {
                        Text(
                            text = "Shimmering Text",
                            modifier = Modifier.shimmer()
                        )
                    } else {
                        Text(
                            text = user.email,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        com.example.preecureapp.R.font.roboto_regular,
                                        FontWeight.Normal
                                    )
                                ),
                                color = Color.Gray,
                                letterSpacing = (0.8).sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        ) }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(110.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .shimmer(),
                verticalAlignment = Alignment.CenterVertically
            ) {}
        }
    }
}

// Row style for options
@Composable
private fun OptionsItemStyle(item: OptionsData, context: Context, navController: NavController) {

    Box(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp)) {
        Row(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(10.dp))
                .clickable(enabled = true) {
                    when(item.title) {
                        "Account" -> navController.navigate(Graph.PROFILE)
                        "My Health" -> navController.navigate(Profile.HealthScreen.route)
                        "Orders" -> navController.navigate(Profile.OrdersScreen.route)
                        "Addresses" -> navController.navigate(Profile.AddressesScreen.route)
                        "Saved Cards" -> navController.navigate(Profile.CardsScreen.route)
                        "Settings" -> navController.navigate(Profile.SettingsScreen.route)
                        "Help Center" -> navController.navigate(Profile.HelpScreen.route)
                        "Offers and Coupons" -> navController.navigate(Profile.OffersScreen.route)
                    }
                }
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = item.icon,
                contentDescription = item.title,
                tint = MaterialTheme.colors.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(weight = 3f, fill = false)
                        .padding(start = 16.dp)
                ) {

                    // Title
                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(
                                    com.example.preecureapp.R.font.roboto_medium,
                                    FontWeight.Medium
                                )
                            )
                        )
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // Sub title
                    Text(
                        text = item.subTitle,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = (0.8).sp,
                            fontFamily = FontFamily(
                                Font(
                                    com.example.preecureapp.R.font.roboto_regular,
                                    FontWeight.Normal
                                )
                            ),
                            color = Color.Gray
                        )
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}

private fun prepareOptionsData() {

    val appIcons = Icons.Outlined

    optionsList.add(
        OptionsData(
            icon = appIcons.Person,
            title = "Account",
            subTitle = "Manage your account"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.ThumbUp,
            title = "My Health",
            subTitle = "Manage your prescriptions and schedules"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.ShoppingCart,
            title = "Orders",
            subTitle = "Orders history"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Person,
            title = "Addresses",
            subTitle = "Your saved addresses"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Star,
            title = "Saved Cards",
            subTitle = "Your saved debit/credit cards"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Settings,
            title = "Settings",
            subTitle = "App notification settings"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Info,
            title = "Help Center",
            subTitle = "FAQs and customer support"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Build,
            title = "Offers and Coupons",
            subTitle = "Offers and coupon codes for you"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Build,
            title = "Sign Out",
            subTitle = "Sign out from the application"
        )
    )
}

data class OptionsData(val icon: ImageVector, val title: String, val subTitle: String)