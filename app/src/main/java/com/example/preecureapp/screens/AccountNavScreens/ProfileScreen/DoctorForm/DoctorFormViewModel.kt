package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preecure.Utils.LoadingState
import com.example.preecureapp.screens.SignupScreen.NewUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class DoctorFormViewModel: ViewModel() {
    private lateinit var firestore: FirebaseFirestore

    var name by mutableStateOf("")
    var speciality by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var selectedStartTime by mutableStateOf("12:00 a.m.")
    var selectedEndTime by mutableStateOf("12:00 a.m.")
    var imageUrl by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    fun insertDoctorUser() {

    }

    fun uploadImageToStorage(imageUri: Uri): Task<Uri> {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("doctor_images/${UUID.randomUUID()}")
        return imageRef.putFile(imageUri).continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { throw it }
            }
            imageRef.downloadUrl
        }
    }

    fun saveImageUrlToFirestore(imageUrl: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore
        user?.uid?.let { uid ->
            db.collection("doctors")
                .document(uid)
                .update("imageUrl", imageUrl)
        }
    }
}