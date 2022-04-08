package com.example.carDock.presentation.dashBoard.fragments.carReg

import androidx.compose.runtime.State

interface CarRegViewModel {

    var carRegFormState: State<CarRegState>

    var formErrorState: State<CarRegErrorState>

    fun onEvent(event: CarRegEvents)
}