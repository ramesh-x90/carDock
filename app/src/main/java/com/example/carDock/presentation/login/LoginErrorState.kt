package com.example.carDock.presentation.login

data class LoginErrorState(
    val emailError : Boolean = false,
    val passwordError : Boolean = false
)
