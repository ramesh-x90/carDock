package com.example.carDock.presentation.login

import androidx.compose.runtime.State

interface LoginViewModel {

    val loginViewState : State<LoginState>
    val loginErrorState : State<LoginErrorState>

    fun onEvent(event : LoginEvents)


}