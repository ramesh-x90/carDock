package com.example.carDock.domain.util

import com.example.carDock.domain.model.Car

data class CarRegValidationResult(
    val car: Car,
    val res: CarRegErrors
)


