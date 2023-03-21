package com.example.preecure.screens.HomeScreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preecure.screens.SignupScreen.newUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ProfileViewModel() : ViewModel() {
    private val db = Firebase.firestore
    private val currentUser = FirebaseAuth.getInstance().currentUser

    val user = mutableStateOf<newUser?>(null)

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    user.value = documentSnapshot.toObject(newUser::class.java)
                }
        }
    }
}
