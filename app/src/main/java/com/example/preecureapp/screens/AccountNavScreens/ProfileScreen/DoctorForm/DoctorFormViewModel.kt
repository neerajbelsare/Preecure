package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preecure.Utils.LoadingState
import com.example.preecureapp.screens.AccountNavScreens.RetrieveUser
import com.example.preecureapp.screens.SignupScreen.NewUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class DoctorFormViewModel: ViewModel() {

    var username by mutableStateOf("")
    var name by mutableStateOf("")
    var speciality by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var startTime by mutableStateOf("12:00 a.m.")
    var endTime by mutableStateOf("12:00 a.m.")
    var experience by mutableStateOf("")
    var qualification by mutableStateOf("")
    var qualificationUrl by mutableStateOf("")
    var identityUrl by mutableStateOf("")
    var profileUrl by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    init {
        isLoading = true
        getData()
        getDoctorData()
    }

    fun getData() {
        val user = mutableStateOf(RetrieveUser())

        viewModelScope.launch {
            user.value = getUserDetails()
            val userDetails = user.value

            username = userDetails.username
        }
    }

    fun getDoctorData() {
        val user = mutableStateOf(DoctorDocuments())

        viewModelScope.launch {
            user.value = getDoctorDetails()
            val doctorDetails = user.value

            qualificationUrl = doctorDetails.qualificationUrl
            identityUrl = doctorDetails.identityUrl
            profileUrl = doctorDetails.profileUrl
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

    suspend fun getDoctorDetails(): DoctorDocuments {

        val fdb = FirebaseFirestore.getInstance()
        var about = DoctorDocuments()
        try {
            isLoading = true
            fdb.collection("users").get().await().map {
                val result = it.toObject(DoctorDocuments::class.java)
                about = result
                isLoading = false
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getUserDetails: $e")
        }
        return about
    }

    fun insertDoctorUser(newDoctorInfo: DoctorInfo) {
        isLoading = true

        val currentUser = FirebaseAuth.getInstance().currentUser
        val firebase = FirebaseFirestore.getInstance()
        val db = firebase.collection("doctors").document(currentUser!!.uid)

        val newDoctor = hashMapOf(
            "name" to newDoctorInfo.name,
            "speciality" to newDoctorInfo.speciality,
            "email" to newDoctorInfo.email,
            "phone" to newDoctorInfo.phone,
            "startTime" to newDoctorInfo.startTime,
            "endTime" to newDoctorInfo.endTime,
            "qualificationUrl" to "https://drive.google.com/file/d/1jaMTdkE-IxTEHRVsHaDwUNTEHm-U8xVw/view?usp=sharing",
            "identityUrl" to "https://drive.google.com/file/d/1jaMTdkE-IxTEHRVsHaDwUNTEHm-U8xVw/view?usp=sharing",
            "profileUrl" to "https://drive.google.com/file/d/1jaMTdkE-IxTEHRVsHaDwUNTEHm-U8xVw/view?usp=sharing"
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
        val imageRef = storageRef.child("doctor_images/${UUID.randomUUID()}")
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
                db.collection("doctors")
                    .document(uid)
                    .update("qualificationUrl", imageUrl)
            }
        }

        if(index == 2) {
            user.uid.let { uid ->
                db.collection("doctors")
                    .document(uid)
                    .update("identityUrl", imageUrl)
            }
        }

        if(index == 3) {
            user.uid.let { uid ->
                db.collection("doctors")
                    .document(uid)
                    .update("profileUrl", imageUrl)
            }
        }

        val updatedDetails = mapOf(
            "isDoctor" to true,
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