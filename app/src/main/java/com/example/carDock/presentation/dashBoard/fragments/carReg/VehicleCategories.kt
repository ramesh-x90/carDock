package com.example.carDock.presentation.dashBoard.fragments.carReg

sealed class VehicleCategories(val titel: String) {
    object Car : VehicleCategories("Car")


}