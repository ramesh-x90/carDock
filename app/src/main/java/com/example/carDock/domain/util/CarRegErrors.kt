package com.example.carDock.domain.util

sealed class CarRegErrors(val error: String) {
    object InvalidBrandError : CarRegErrors("Invalid brand")
    object InvalidModelError : CarRegErrors("Invalid model")
    object InvalidPriceError : CarRegErrors("Invalid price")
    object InvalidChassisNoError : CarRegErrors("Invalid chassis no")
    object InvalidEngineNoError : CarRegErrors("Invalid engine no")
    object InvalidFuelTypeError : CarRegErrors("Invalid fuel no")
    object InvalidSellerError : CarRegErrors("Invalid seller ID")
    object InvalidLaunchYear : CarRegErrors("Invalid Launched Year")
    object None : CarRegErrors("No Error")
}
