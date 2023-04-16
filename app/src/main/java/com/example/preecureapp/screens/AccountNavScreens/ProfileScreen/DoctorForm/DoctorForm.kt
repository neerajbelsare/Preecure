package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

import android.widget.Toast
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.GlideImage
import com.example.preecure.screens.HomeScreen.OptionsData
import com.example.preecure.ui.theme.MainColor
import com.example.preecureapp.navigation.AuthScreen
import com.example.preecureapp.navigation.nav_graph.Graph
import com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.CustomTopAppBarWithBackButton

@Composable
fun DoctorForm(navController: NavController,
                 doctorFormViewModel: DoctorFormViewModel = viewModel()) {
    val allInputsFilled =
        doctorFormViewModel.name.isNotBlank() && doctorFormViewModel.speciality.isNotBlank()
                && doctorFormViewModel.email.isNotBlank() && doctorFormViewModel.phone.isNotBlank()
                && doctorFormViewModel.startTime.isNotBlank() && doctorFormViewModel.endTime.isNotBlank()
                && doctorFormViewModel.experience.isNotBlank() && doctorFormViewModel.qualification.isNotBlank()

    Scaffold(
        topBar = {
            CustomTopAppBarWithBackButton(
                navController = navController,
                appBarTitle = "Doctor Application Form",
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
                    painter = painterResource(id = com.example.preecureapp.R.drawable.doctor),
                    contentDescription = "Register as a Doctor",
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
                value = doctorFormViewModel.name,
                onValueChange = { doctorFormViewModel.name = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Speciality/Area of Expertise",
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
                value = doctorFormViewModel.speciality,
                onValueChange = { doctorFormViewModel.speciality = it },
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
                value = doctorFormViewModel.email,
                onValueChange = { doctorFormViewModel.email = it },
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
                value = doctorFormViewModel.phone,
                onValueChange = { doctorFormViewModel.phone = it },

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
                Text(doctorFormViewModel.startTime,
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
                            doctorFormViewModel.startTime = s
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
                Text(doctorFormViewModel.endTime,
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
                            doctorFormViewModel.endTime = s
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
                text = "Years of Experience",
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
                value = doctorFormViewModel.experience,
                onValueChange = { doctorFormViewModel.experience = it },

                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
                maxLines = 1,
                visualTransformation = LimitInputToTwoCharactersVisualTransformation()
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Educational Qualification",
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
                value = doctorFormViewModel.qualification,
                onValueChange = { doctorFormViewModel.qualification = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MainColor,
                    unfocusedBorderColor = Color(0xFFDADADA),
                ),
            )

            Spacer(modifier = Modifier.height(28.dp))

            if (doctorFormViewModel.isLoading) {
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
                        doctorFormViewModel.insertDoctorUser(DoctorInfo(doctorFormViewModel.name,
                            doctorFormViewModel.speciality, doctorFormViewModel.email, doctorFormViewModel.phone,
                        doctorFormViewModel.startTime, doctorFormViewModel.endTime, doctorFormViewModel.experience,
                        doctorFormViewModel.qualification))
                        navController.navigate(Graph.DOCTOR) },

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

class LimitInputToTwoCharactersVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText = text.text.take(2) // Limit to 2 characters
        return TransformedText(AnnotatedString(newText), OffsetMapping.Identity)
    }
}

