package com.example.preecureapp.screens.AccountNavScreens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.preecureapp.R
import com.example.preecureapp.navigation.nav_graph.Graph
import com.example.preecureapp.screens.SignupScreen.NewUser
import com.example.preecureapp.screens.SignupScreen.SignUpViewModel
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.preecure.Utils.LoadingState
import com.example.preecureapp.navigation.AuthScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun DoctorForm(navController: NavController,
                 doctorFormViewModel: DoctorFormViewModel = viewModel()) {
//    val allInputsFilled = doctorFormViewModel.email.isNotBlank() && doctorFormViewModel.profession.isNotBlank()
//            && doctorFormViewModel.name.isNotBlank() && doctorFormViewModel.registrationId.isNotBlank()
//
//    val status by doctorFormViewModel.loadingState.collectAsState()
//    val context = LocalContext.current
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .background(Color.White)
//    ) {
//        Image(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(132.dp)
//                .padding(start = 20.dp)
//                .clip(shape = CircleShape),
//            painter = painterResource(id = com.example.preecureapp.R.drawable.doctor),
//            contentDescription = "Your Image"
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp),
////        verticalArrangement = Arrangement.Bottom
//    ) {
//        Text(
//            text = "Sign Up",
//            style = MaterialTheme.typography.h4,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//        OutlinedTextField(
//            value = doctorFormViewModel.name,
//            onValueChange = { doctorFormViewModel.name = it },
//            label = { Text("Name") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//        OutlinedTextField(
//            value = doctorFormViewModel.profession,
//            onValueChange = { doctorFormViewModel.profession = it },
//            label = { Text("Profession") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Next,
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//        OutlinedTextField(
//            value = doctorFormViewModel.email,
//            onValueChange = { doctorFormViewModel.email = it },
//            label = { Text("Email address") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//        OutlinedTextField(
//            value = doctorFormViewModel.registrationId,
//            onValueChange = { doctorFormViewModel.registrationId = it },
//            label = { Text("Registration ID") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Password,
//                imeAction = ImeAction.Done
//            ),
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier
//                .fillMaxWidth()
//        )
//        if (doctorFormViewModel.isLoading) {
//            Button(
//                onClick = {},
//                modifier = Modifier
//                    .fillMaxWidth(),
//                enabled = allInputsFilled
//            ) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .size(30.dp),
//                    color = Color.White,
//                    strokeWidth = 3.dp
//                )
//            }
//        } else {
//            Button(
//                onClick = { doctorFormViewModel.register(doctorInfo(doctorFormViewModel.name, doctorFormViewModel.profession, doctorFormViewModel.email, doctorFormViewModel.registrationId)) },
//                modifier = Modifier
//                    .fillMaxWidth(),
//                enabled = allInputsFilled
//            ) {
//                Text("Sign Up")
//            }
//        }
//        if (doctorFormViewModel.isError) {
//            Text(
//                modifier = Modifier
//                    .padding(top = 10.dp),
//                color = Color.Red,
//                text = doctorFormViewModel.errorMessage
//            )
//        }
//
//        Spacer(modifier = Modifier
//            .height(30.dp))
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Or",
//                modifier = Modifier.padding(bottom = 16.dp),
//                color = Color.Gray
//            )
//        }
//
//        Spacer(modifier = Modifier
//            .height(1.dp))
//        }
//
//        Spacer(modifier = Modifier
//            .height(1.dp))
    }
