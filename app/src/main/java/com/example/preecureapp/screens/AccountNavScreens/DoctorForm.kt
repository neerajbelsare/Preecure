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
                 signUpViewModel: DoctorFormViewModel = viewModel()) {
    val allInputsFilled = signUpViewModel.email.isNotBlank() && signUpViewModel.registrationId.isNotBlank()
            && signUpViewModel.name.isNotBlank() && signUpViewModel.registrationId.isNotBlank()

    val status by signUpViewModel.loadingState.collectAsState()
    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Doctor Registration",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = signUpViewModel.name,
            onValueChange = { signUpViewModel.name = it },
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = signUpViewModel.profession,
            onValueChange = { signUpViewModel.profession = it },
            label = { Text("Profession") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = signUpViewModel.email,
            onValueChange = { signUpViewModel.email = it },
            label = { Text("Email address") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = signUpViewModel.registrationId ,
            onValueChange = { signUpViewModel.registrationId = it },
            label = { Text("Registration number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )
        if (signUpViewModel.isLoading) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(),
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
                onClick = { signUpViewModel.signUp(doctorInfo(signUpViewModel.name, signUpViewModel.profession, signUpViewModel.email, signUpViewModel.registrationId)) },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = allInputsFilled
            ) {
                Text("Register")
            }
        }
        if (signUpViewModel.isError) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                color = Color.Red,
                text = signUpViewModel.errorMessage
            )
        }

        if (signUpViewModel.isSignedUp) {
            LaunchedEffect(Unit) {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            }
        }

        Spacer(modifier = Modifier
            .height(30.dp))


        Spacer(modifier = Modifier
            .height(1.dp))


    }

    if(signUpViewModel.isSignedUp) {
        navController.navigate(Graph.HOME)
    }

    when (status.status) {
        LoadingState.Status.FAILED -> {
            Toast.makeText(context, status.msg ?: "Error", Toast.LENGTH_SHORT).show()
        }
        else -> {}
    }
}
