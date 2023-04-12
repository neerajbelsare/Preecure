package com.example.preecureapp.screens.AccountNavScreens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preecure.Utils.LoadingState
import com.example.preecureapp.screens.SignupScreen.NewUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.Instant

class AccountViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore

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




    private val db = Firebase.firestore
    private val currentUser = FirebaseAuth.getInstance().currentUser
    val user = mutableStateOf(RetrieveUser())

    init {
        isLoading = true
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            user.value = getUserDetails()
            val userDetails = user.value
            name = userDetails.name
            phone = userDetails.phoneNumber
            email = userDetails.email
        }
    }

    suspend fun getUserDetails(): RetrieveUser {

        val fdb = FirebaseFirestore.getInstance()
        var about = RetrieveUser()
        try {
            isLoading = true
            fdb.collection("users").get().await().map {
                val result = it.toObject(RetrieveUser::class.java)
                about = result
                isLoading = false
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getUserDetails: $e")
        }
        return about
    }

    fun updateUserDetails(userId: String, name: String, email: String) {
        db.collection("users").document(userId)
            .update(
                mapOf(
                    "name" to name,
                    "email" to email
                )
            )
            .addOnSuccessListener {
                // Update successful
            }
            .addOnFailureListener { e ->
                // Handle errors here
            }
    }

}