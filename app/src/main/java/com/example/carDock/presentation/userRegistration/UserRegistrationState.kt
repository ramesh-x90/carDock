package com.example.carDock.presentation.userRegistration

data class UserRegistrationState(
    val userName: String = "",
    val email: String = "",
    val address: String = "",
    val password: String = "",
    val conformPassword: String = "",
    val contact_number: String = "",
    val loading: Boolean = false,
)
