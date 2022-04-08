package com.example.carDock.domain.util

sealed class UserRegResult {
    sealed class Failed(val error: String) : UserRegResult() {
        object InvalidUserName : Failed("Invalid user name")
        object InvalidEmail : Failed("Invalid Email")
        object InvalidPassword : Failed("Invalid Password")
        object InvalidAddress : Failed("Invalid Address")
        object InvalidContactNumber : Failed("Invalid Contact Number")
        object InvalidInitBalance : Failed("Invalid Initial Balance")
        data class DbError(val details: String) : Failed("DB error")


    }

    object Success : UserRegResult()

}

