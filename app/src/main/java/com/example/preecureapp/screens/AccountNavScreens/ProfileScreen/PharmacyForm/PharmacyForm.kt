package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.preecure.ui.theme.MainColor
import com.example.preecureapp.navigation.nav_graph.Graph
import com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.CustomTopAppBarWithBackButton

@Composable
fun PharmacyForm(navController: NavController,
               pharmacyFormViewModel: PharmacyFormViewModel = viewModel()) {
    val allInputsFilled =
        pharmacyFormViewModel.name.isNotBlank() && pharmacyFormViewModel.address.isNotBlank()
                && pharmacyFormViewModel.email.isNotBlank() && pharmacyFormViewModel.phone.isNotBlank()
                && pharmacyFormViewModel.startTime.isNotBlank() && pharmacyFormViewModel.endTime.isNotBlank()
                && pharmacyFormViewModel.latitude.isNotBlank() && pharmacyFormViewModel.longitude.isNotBlank()

    Scaffold(
        topBar = {
            CustomTopAppBarWithBackButton(
                navController = navController,
                appBarTitle = "Pharmacy Application Form",
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
                Image(
                    painter = painterResource(id = com.example.preecureapp.R.drawable.pharmacy),
                    contentDescription = "Register as a Pharmacy",
                    modifier = Modifier
                        .fillMaxWidth()
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
                        com.example.preecureapp.R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = pharmacyFormViewModel.name,
                onValueChange = { pharmacyFormViewModel.name = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Permanent Address",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        com.example.preecureapp.R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = pharmacyFormViewModel.address,
                onValueChange = { pharmacyFormViewModel.address = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Email Address",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        com.example.preecureapp.R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = pharmacyFormViewModel.email,
                onValueChange = { pharmacyFormViewModel.email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Phone Number",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        com.example.preecureapp.R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = pharmacyFormViewModel.phone,
                onValueChange = { pharmacyFormViewModel.phone = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Availability(office hours, on-call hours)",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        com.example.preecureapp.R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            var expanded by remember { mutableStateOf(false) }
            val items: ArrayList<String> = ArrayList()
            items.add("12:00 a.m.")
            items.add("12:30 a.m.")
            items.add("1:00 a.m.")
            items.add("1:30 a.m.")
            items.add("2:00 a.m.")
            items.add("2:30 a.m.")
            items.add("3:00 a.m.")
            items.add("3:30 a.m.")
            items.add("4:00 a.m.")
            items.add("4:30 a.m.")
            items.add("5:00 a.m.")
            items.add("5:30 a.m.")
            items.add("6:00 a.m.")
            items.add("6:30 a.m.")
            items.add("7:00 a.m.")
            items.add("7:30 a.m.")
            items.add("8:00 a.m.")
            items.add("8:30 a.m.")
            items.add("9:00 a.m.")
            items.add("9:30 a.m.")
            items.add("10:00 a.m.")
            items.add("10:30 a.m.")
            items.add("11:00 a.m.")
            items.add("11:30 a.m.")
            items.add("12:00 a.m.")
            items.add("12:30 a.m.")
            items.add("1:00 p.m.")
            items.add("1:30 p.m.")
            items.add("2:00 p.m.")
            items.add("2:30 p.m.")
            items.add("3:00 p.m.")
            items.add("3:30 p.m.")
            items.add("4:00 p.m.")
            items.add("4:30 p.m.")
            items.add("5:00 p.m.")
            items.add("5:30 p.m.")
            items.add("6:00 p.m.")
            items.add("6:30 p.m.")
            items.add("7:00 p.m.")
            items.add("7:30 p.m.")
            items.add("8:00 p.m.")
            items.add("8:30 p.m.")
            items.add("9:00 p.m.")
            items.add("9:30 p.m.")
            items.add("10:00 p.m.")
            items.add("10:30 p.m.")
            items.add("11:00 p.m.")
            items.add("11:30 p.m.")

            val disabledValue = "B"
            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)) {
                Text(pharmacyFormViewModel.startTime,
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp)
                        .border(width = 1.dp, color = Color(0xFFDADADA), RoundedCornerShape(5.dp))
                        .clickable(onClick = { expanded = true }),
                    textAlign = TextAlign.Center
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    items.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            pharmacyFormViewModel.startTime = s
                            expanded = false
                        }) {
                            val disabledText = if (s == disabledValue) {
                                " (Disabled)"
                            } else {
                                ""
                            }
                            Text(text = s + disabledText)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(18.dp))

            Text(text = "to",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
            )
            Spacer(modifier = Modifier.height(18.dp))

            var expanded1 by remember { mutableStateOf(false) }
            val items1: ArrayList<String> = ArrayList()
            items1.add("12:00 a.m.")
            items1.add("12:30 a.m.")
            items1.add("1:00 a.m.")
            items1.add("1:30 a.m.")
            items1.add("2:00 a.m.")
            items1.add("2:30 a.m.")
            items1.add("3:00 a.m.")
            items1.add("3:30 a.m.")
            items1.add("4:00 a.m.")
            items1.add("4:30 a.m.")
            items1.add("5:00 a.m.")
            items1.add("5:30 a.m.")
            items1.add("6:00 a.m.")
            items1.add("6:30 a.m.")
            items1.add("7:00 a.m.")
            items1.add("7:30 a.m.")
            items1.add("8:00 a.m.")
            items1.add("8:30 a.m.")
            items1.add("9:00 a.m.")
            items1.add("9:30 a.m.")
            items1.add("10:00 a.m.")
            items1.add("10:30 a.m.")
            items1.add("11:00 a.m.")
            items1.add("11:30 a.m.")
            items1.add("12:00 a.m.")
            items1.add("12:30 a.m.")
            items1.add("1:00 p.m.")
            items1.add("1:30 p.m.")
            items1.add("2:00 p.m.")
            items1.add("2:30 p.m.")
            items1.add("3:00 p.m.")
            items1.add("3:30 p.m.")
            items1.add("4:00 p.m.")
            items1.add("4:30 p.m.")
            items1.add("5:00 p.m.")
            items1.add("5:30 p.m.")
            items1.add("6:00 p.m.")
            items1.add("6:30 p.m.")
            items1.add("7:00 p.m.")
            items1.add("7:30 p.m.")
            items1.add("8:00 p.m.")
            items1.add("8:30 p.m.")
            items1.add("9:00 p.m.")
            items1.add("9:30 p.m.")
            items1.add("10:00 p.m.")
            items1.add("10:30 p.m.")
            items1.add("11:00 p.m.")
            items1.add("11:30 p.m.")

            val disabledValue1 = "B"
            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)) {
                Text(pharmacyFormViewModel.endTime,
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp)
                        .border(width = 1.dp, color = Color(0xFFDADADA), RoundedCornerShape(5.dp))
                        .clickable(onClick = { expanded1 = true }),
                    textAlign = TextAlign.Center
                )
                DropdownMenu(
                    expanded = expanded1,
                    onDismissRequest = { expanded1 = false },
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    items1.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            pharmacyFormViewModel.endTime = s
                            expanded1 = false
                        }) {
                            val disabledText = if (s == disabledValue1) {
                                " (Disabled)"
                            } else {
                                ""
                            }
                            Text(text = s + disabledText)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Exact Location of your Pharmacy (Latitude, Longitude)",
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 10.dp),
                color = Color(0xFF474747),
                fontFamily = FontFamily(
                    Font(
                        com.example.preecureapp.R.font.googlesansdisplay_bold,
                        FontWeight.Bold
                    )
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = pharmacyFormViewModel.latitude,
                onValueChange = { pharmacyFormViewModel.latitude = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(28.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                value = pharmacyFormViewModel.longitude,
                onValueChange = { pharmacyFormViewModel.longitude = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
            )

            Spacer(modifier = Modifier.height(28.dp))

            if (pharmacyFormViewModel.isLoading) {
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
                    onClick = {
                        pharmacyFormViewModel.insertPharmacyUser(PharmacyInfo(pharmacyFormViewModel.name,
                            pharmacyFormViewModel.address, pharmacyFormViewModel.email, pharmacyFormViewModel.phone,
                        pharmacyFormViewModel.startTime, pharmacyFormViewModel.endTime, pharmacyFormViewModel.latitude,
                        pharmacyFormViewModel.longitude))
                        navController.navigate(Graph.PHARMACY) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(40.dp),
                    enabled = allInputsFilled
                ) {
                    Text("Next")
                }
            }

            Spacer(modifier = Modifier.height(38.dp))
        }
    }
}

