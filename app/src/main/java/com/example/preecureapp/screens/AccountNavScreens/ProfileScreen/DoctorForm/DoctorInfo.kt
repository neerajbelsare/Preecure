package com.example.preecureapp.screens.AccountNavScreens.ProfileScreen.DoctorForm

data class DoctorInfo(val name: String="",
                      val speciality: String="",
                      val email: String="",
                      val phone: String="",
                      val startTime: String="",
                      val endTime: String="",
                      val experience: String="",
                      val qualification: String="")

data class DoctorDocuments(val qualificationUrl: String="",
                            val identityUrl: String="",
                            val profileUrl: String="")
