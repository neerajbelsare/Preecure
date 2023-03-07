package com.example.preecure

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.ui.res.painterResource

@Preview
@Composable
fun SignInScreen(signInViewModel: SignInViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = signInViewModel.email,
            onValueChange = { signInViewModel.email = it },
            label = { Text("Email address") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = signInViewModel.password,
            onValueChange = { signInViewModel.password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )
        if (signInViewModel.isLoading) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
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
                onClick = { signInViewModel.signIn() },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Sign In")
            }
        }
        if (signInViewModel.isError) {
            Text(
                text = signInViewModel.errorMessage,
                color = MaterialTheme.colors.error
            )
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Forgot password?")
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
            .fillMaxWidth()) {
            Button(
                modifier = Modifier,
                elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                onClick = { /* Handle button click */ }
            ) {
                Image(
                    painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google Icon"
                )
            }

            Button(
                modifier = Modifier,
                elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                onClick = { /* Handle button click */ }
            ) {
                Image(
                    painterResource(id = R.drawable.facebook_icon),
                    contentDescription = "Facebook Icon"
                )
            }

            Button(
                modifier = Modifier,
                elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                onClick = { /* Handle button click */ }
            ) {
                Image(
                    painterResource(id = R.drawable.apple_icon),
                    contentDescription = "Apple Icon"
                )
            }
        }
    }
}
