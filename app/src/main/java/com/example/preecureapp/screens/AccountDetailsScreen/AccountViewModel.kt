package com.example.preecureapp.screens.AccountDetailsScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preecureapp.screens.SignupScreen.NewUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AccountViewModel : ViewModel() {
    private var isLoading by mutableStateOf(false)

    private val db = Firebase.firestore
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val user = mutableStateOf<NewUser?>(null)

    init {
        isLoading = true
        getData()
    }
    private fun getData() {
        viewModelScope.launch {
            user.value = getUserDetails()
        }
    }

    suspend fun getUserDetails(): NewUser {

        val fdb = FirebaseFirestore.getInstance()
        var about = NewUser()
        try {
            isLoading = true
            fdb.collection("users").get().await().map {
                val result = it.toObject(NewUser::class.java)
                about = result
                isLoading = false
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getUserDetails: $e")
        }
        return about
    }
}