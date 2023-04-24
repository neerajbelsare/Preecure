package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preecureapp.screens.AccountNavScreens.RetrieveUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class LabFormViewModel: ViewModel() {

    var username by mutableStateOf("")
    var name by mutableStateOf("")
    var address by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var startTime by mutableStateOf("12:00 a.m.")
    var endTime by mutableStateOf("12:00 a.m.")
    var latitude by mutableStateOf("")
    var longitude by mutableStateOf("")
    var imageUrl by mutableStateOf("")
    var proofUrl by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    init {
        isLoading = true
        getData()
        getLabData()
    }

    fun getData() {
        val user = mutableStateOf(RetrieveUser())

        viewModelScope.launch {
            user.value = getUserDetails()
            val userDetails = user.value

            username = userDetails.username
        }
    }

    fun getLabData() {
        val user = mutableStateOf(LabDocuments())

        viewModelScope.launch {
            user.value = getLabDetails()
            val labDetails = user.value

            imageUrl = labDetails.imageUrl
            proofUrl = labDetails.proofUrl
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

    suspend fun getLabDetails(): LabDocuments {

        val fdb = FirebaseFirestore.getInstance()
        var about = LabDocuments()
        try {
            isLoading = true
            fdb.collection("users").get().await().map {
                val result = it.toObject(LabDocuments::class.java)
                about = result
                isLoading = false
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getUserDetails: $e")
        }
        return about
    }

    fun insertLabUser(newLabInfo: LabInfo) {
        isLoading = true

        val currentUser = FirebaseAuth.getInstance().currentUser
        val firebase = FirebaseFirestore.getInstance()
        val db = firebase.collection("labs").document(currentUser!!.uid)

        val newDoctor = hashMapOf(
            "name" to newLabInfo.name,
            "address" to newLabInfo.address,
            "email" to newLabInfo.email,
            "phone" to newLabInfo.phone,
            "startTime" to newLabInfo.startTime,
            "endTime" to newLabInfo.endTime,
            "imageUrl" to "https://drive.google.com/file/d/1jaMTdkE-IxTEHRVsHaDwUNTEHm-U8xVw/view?usp=sharing",
            "proofUrl" to "https://drive.google.com/file/d/1jaMTdkE-IxTEHRVsHaDwUNTEHm-U8xVw/view?usp=sharing"
        )

        db
        .set(newDoctor)
        .addOnSuccessListener {
            isLoading = false
        }
        .addOnFailureListener {
            isLoading = false
            isError = true
        }
    }

    fun uploadImageToStorage(imageUri: Uri): Task<Uri> {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("lab_images/${UUID.randomUUID()}")
        return imageRef.putFile(imageUri).continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { throw it }
            }
            imageRef.downloadUrl
        }
    }

    fun saveImageUrlToFirestore(imageUrl: String, index: Int) {
        val user = FirebaseAuth.getInstance().currentUser
        val firebase = FirebaseFirestore.getInstance()
        val userDocRef = firebase.collection("users").document(user!!.uid)

        val db = Firebase.firestore

        if(index == 1) {
            user.uid.let { uid ->
                db.collection("labs")
                    .document(uid)
                    .update("imageUrl", imageUrl)
            }
        }

        if(index == 2) {
            user.uid.let { uid ->
                db.collection("labs")
                    .document(uid)
                    .update("proofUrl", imageUrl)
            }
        }

        val updatedDetails = mapOf(
            "isLab" to true,
        )

        userDocRef.update(updatedDetails)
            .addOnSuccessListener {
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = false
            }
    }
}