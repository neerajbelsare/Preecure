package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.preecure.ui.theme.MainColor
import com.example.preecureapp.R
import com.example.preecureapp.navigation.AuthScreen
import com.example.preecureapp.navigation.FormScreen
import com.example.preecureapp.navigation.LabScreen
import com.example.preecureapp.navigation.nav_graph.Graph

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AccountScreen(navController: NavController, accountViewModel: AccountViewModel = viewModel()) {
    val allInputsFilled = accountViewModel.email.isNotBlank() && accountViewModel.password.isNotBlank()
            && accountViewModel.password.isNotBlank() && accountViewModel.phone.isNotBlank()
    Scaffold(
        topBar = {
            CustomTopAppBarWithBackButton(
                navController = navController,
                appBarTitle = "Profile",
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        it
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                    uri?.let {
                        accountViewModel.uploadImageToStorage(it).addOnSuccessListener { imageUrl ->
                            accountViewModel.saveImageUrlToFirestore(imageUrl.toString())
                        }
                    }
                }

                if (accountViewModel.imageUrl.isNotBlank()) {
                    GlideImage(
                        model = accountViewModel.imageUrl,
                        contentDescription = "Your Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 20.dp)
                            .size(132.dp)
                            .clip(CircleShape)
                            .border(7.dp, Color(0xFFF7F7F7), CircleShape)
                            .align(Alignment.Center)
                            .scale(1f, 1f)
                            .clickable { launcher.launch("image/*") },
                    )
                }
                Text(
                    text = "@" + accountViewModel.username,
                    color = Color(0xFF8D8D8D),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 16.dp, bottom = 0.dp, top = 160.dp)
                        .scale(1f, 1f),
                    fontFamily = FontFamily(
                        Font(
                            R.font.googlesansdisplay_medium,
                            FontWeight.Medium
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Name",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = accountViewModel.name,
                onValueChange = {accountViewModel.name = it},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Email",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = accountViewModel.email,
                onValueChange = {accountViewModel.email = it},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Password",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = accountViewModel.password,
                onValueChange = {accountViewModel.password = it},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            TextButton(onClick = {navController.navigate(AuthScreen.SignInScreen.route)}) {
                Text(text = "Reset Password")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Phone Number",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = accountViewModel.phone,
                onValueChange = {accountViewModel.phone = it},

                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(15.dp))

            if (accountViewModel.isLoading) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(40.dp),
                    enabled = allInputsFilled
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(30.dp),
                        color = Color.White,
                        strokeWidth = 3.dp
                    )
                }
            } else {
                Button(
                    onClick = { accountViewModel.updateUserDetails() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(40.dp),
                    enabled = allInputsFilled
                ) {
                    Text("Save")
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = Color(0xFFDADADA),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(38.dp))

            Text(
                text = "Apply for a Special Role",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(64.dp)
                    .background(Color.White)
                    .border(1.dp, Color(0xFFDADADA), RoundedCornerShape(10.dp))
                    .clickable(onClick = {
                        navController.navigate(Graph.FORMS)
                    })
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "I am a Doctor",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painterResource(R.drawable.ic_arrowforward),
                    contentDescription = "Forward",
                    tint = Color(0xFFAFAFAF)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(64.dp)
                    .background(Color.White)
                    .border(1.dp, Color(0xFFDADADA), RoundedCornerShape(10.dp))
                    .clickable(onClick = {
                        navController.navigate(FormScreen.Lab.route)
                    })
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "I own a Lab",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painterResource(R.drawable.ic_arrowforward),
                    contentDescription = "Forward",
                    tint = Color(0xFFAFAFAF)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(64.dp)
                    .background(Color.White)
                    .border(1.dp, Color(0xFFDADADA), RoundedCornerShape(10.dp))
                    .clickable(onClick = {
                        navController.navigate(FormScreen.Pharmacy.route)
                    })
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "I own a Pharmacy",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painterResource(R.drawable.ic_arrowforward),
                    contentDescription = "Forward",
                    tint = Color(0xFFAFAFAF)
                )
            }
        }
    }
}

@Composable
fun CustomTopAppBarWithBackButton(
    navController: NavController,
    appBarTitle: String,
    backgroundColor: Color
) {
    TopAppBar(
        modifier = Modifier
            .padding(top = 40.dp),
        title = {
            Text(text = appBarTitle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(280.dp))
                },
        backgroundColor = backgroundColor,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        elevation = 0.dp
    )
}
