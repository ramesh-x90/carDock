package com.example.carDock.presentation.userRegistration

data class UserRegErrorState(
    val userNameError: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val confirmPasswordError: Boolean = false,
    val addressError: Boolean = false,
    val contactNoError: Boolean = false,
)
