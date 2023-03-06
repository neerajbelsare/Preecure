@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.preecure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignInScreen()
        }
    }
}


//@Composable
//fun SignInScreen(viewModel: SignInViewModel = viewModel()) {
//    Box(
//        modifier = Modifier
//            .size(200.dp)
//            .shadow(elevation = 16.dp, shape = RoundedCornerShape(8.dp))
//    ) {
//       Column(modifier = Modifier) {
//           OutlinedTextField(
//               value = viewModel.email,
//               onValueChange = { viewModel.email = it },
//               modifier = Modifier
//                   .fillMaxWidth()
//           )
//       }
//    }
//}

@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Test",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(CenterHorizontally)
        )

        Card(
            modifier = Modifier
                .weight(2f),
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(CenterHorizontally)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Email address") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)
            )
            Button(
                onClick = {},
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(text = "Sign In")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Forgot password?")
            }

            Row(modifier = Modifier
            ) {

            }
    }
    }
}
