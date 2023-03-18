package com.example.preecure.screens.SignupScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preecure.MainActivity
import com.example.preecure.Utils.LoadingState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel : ViewModel() {
    var name by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isEmpty by mutableStateOf(false)
    var isSignedUp by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    val loadingState = MutableStateFlow(LoadingState.IDLE)

    fun signUp(user: newUser) {
        isLoading = true
        isError = false

        val auth = Firebase.auth
        val db = Firebase.firestore
        val username = generateUniqueUsername(name, db)

        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnSuccessListener {
                    val newUser = hashMapOf(
                        "name" to user.name,
                        "email" to user.email,
                        "phone" to user.phoneNumber,
                        "password" to user.password,
                        "username" to username
                    )

                    db.collection("users")
                        .document(it.user!!.uid)
                        .set(newUser)
                        .addOnSuccessListener { isLoading = false
                        isSignedUp = true}
                        .addOnFailureListener {
                            errorMessage = "There was an error! Please try again!"
                            isLoading = false
                            isError = true
                        }
                }
                .addOnFailureListener { exception ->
                    if (exception is FirebaseAuthUserCollisionException) {
                        val errorCode = exception.errorCode
                        if (errorCode == "ERROR_EMAIL_ALREADY_IN_USE") {
                            errorMessage = "The user with the provided email already exists."
                        }
                        errorMessage = "There was an error while signing up! Please try again!"
                        isLoading = false
                        isError = true
                    }
                }
            }
            else{
                isEmpty = true
            }
    }

    fun signWithGoogleCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithCredential(credential).await()
            loadingState.emit(LoadingState.LOADED)
        } catch (e: Exception) {
            loadingState.emit(LoadingState.error(e.localizedMessage))
        }
    }

    private fun generateRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun generateUniqueUsername(name: String, db: FirebaseFirestore): String {
        val username = name.replace(" ", "") // remove spaces from name
        var suffix = generateRandomString(6) // generate initial suffix
        var usernameWithSuffix = "$username$suffix" // concatenate username and suffix
        var usernameExists = true // flag to check uniqueness
        while (usernameExists) {
            // check if the username is unique by querying Firestore
            val query = db.collection("users").whereEqualTo("username", usernameWithSuffix)
            val result = query.get().addOnSuccessListener {
                usernameExists = false
            }
//            if (result.isEmpty) {
//                isUnique = true
//            } else {
//                // if the username is not unique, generate a new suffix and try again
//                suffix = generateRandomString(6)
//                usernameWithSuffix = "$username$suffix"
//            }
        }
        return usernameWithSuffix // return the unique username
    }

}