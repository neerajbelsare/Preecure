package com.example.preecure

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isEmpty by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    private lateinit var user: FirebaseAuth

    fun signIn() {
        isLoading = true
        isError = false

        user = FirebaseAuth.getInstance()

        if(email.isNotEmpty() && password.isNotEmpty()){
            user.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity()){
                    task ->
                if(task.isSuccessful){
                    isLoading = false
                }else{
                    isError = true
                }
            }
        }else{
            isEmpty = true
        }
    }
}