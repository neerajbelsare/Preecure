package com.example.preecure.screens.HomeScreen

import androidx.lifecycle.ViewModel
import com.example.preecure.screens.SignupScreen.newUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ProfileViewModel(private val firestore: FirebaseFirestore) : ViewModel() {
    fun getUserDetails(uid: String): Flow<newUser> = flow {
        val userRef = firestore.collection("users").document(uid)
        val snapshot = userRef.get().await()
        val userDetails = snapshot.toObject<newUser>()
        emit(userDetails!!)
    }
}
