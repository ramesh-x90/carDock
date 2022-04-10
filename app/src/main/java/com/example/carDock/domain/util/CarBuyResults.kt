package com.example.carDock.domain.util

sealed class CarBuyResults {
    sealed class Failed(val error : String) : CarBuyResults()
    {
        object NotEnoughBalance : Failed("Not Enough Balance")
        object InvalidCar : Failed("Invalid Car")
        object CarIsAlreadySold : Failed("Car Is AlreadySold")
        object InvalidUserCredential : Failed("Invalid User Credential")
    }

    object Success : CarBuyResults()
}