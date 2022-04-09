package com.example.carDock.presentation.login

sealed class LoginEvents {

    data class OnEmailChange(val data : String) : LoginEvents()
    data class OnPasswordChange(val data : String) : LoginEvents()
    data class OnLoginSubmit(
        val onSuccess : (it : String)-> Unit ,
        val onFailed : (it : String) -> Unit
    ) : LoginEvents()

}
