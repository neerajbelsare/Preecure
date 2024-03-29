package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

data class PharmacyInfo(val name: String="",
                        val address: String="",
                        val email: String="",
                        val phone: String="",
                        val startTime: String="",
                        val endTime: String="",
                        val latitude: String="",
                        val longitude: String="")

data class PharmacyDocuments(val imageUrl: String="",
                             val proofUrl: String="")
