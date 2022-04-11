package com.example.carDock.presentation.dashBoard.fragments.carReg

data class CarRegErrorState(
    val brandError: Boolean = false,
    val modelError: Boolean = false,
    val priceError: Boolean = false,
    val chassisNoError: Boolean = false,
    val engineNoError: Boolean = false,
    val fuelTypeError: Boolean = false,
    val yearError: Boolean = false,
)
