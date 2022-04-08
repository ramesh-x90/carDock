package com.example.carDock.domain.util

sealed class CarRegErrors(val error: String) {
    object InvalidBrandError : CarRegErrors("invalid brand")
    object InvalidModelError : CarRegErrors("invalid model")
    object InvalidPriceError : CarRegErrors("invalid price")
    object InvalidChassisNoError : CarRegErrors("invalid chassis no")
    object InvalidEngineNoError : CarRegErrors("invalid engine no")
    object InvalidFuelTypeError : CarRegErrors("invalid fuel no")
    object InvalidSellerError : CarRegErrors("invalid seller ID")
    object None : CarRegErrors("No Error")
}
