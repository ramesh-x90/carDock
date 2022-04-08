package com.example.carDock.presentation.dashBoard.fragments.carList

import com.example.carDock.domain.model.Car

data class CarListState(
    var carList: List<Car> = emptyList()
)