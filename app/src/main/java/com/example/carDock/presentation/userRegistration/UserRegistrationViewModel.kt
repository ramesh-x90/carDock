package com.example.carDock.presentation.userRegistration

import androidx.compose.runtime.State

interface UserRegistrationViewModel {
    val userRegState: State<UserRegistrationState>
    val userRegErrorState: State<UserRegErrorState>
    fun onEvent(event: UserRegistrationEvents)
}