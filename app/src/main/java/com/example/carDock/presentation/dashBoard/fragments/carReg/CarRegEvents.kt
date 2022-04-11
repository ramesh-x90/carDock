package com.example.carDock.presentation.dashBoard.fragments.carReg

import androidx.compose.ui.graphics.Color
import com.example.carDock.domain.util.FuelTypes

sealed class CarRegEvents {

    data class OnBrandChange(val text: String) : CarRegEvents()
    data class OnBrandSelect(val brand: String) : CarRegEvents()

    data class OnYearChange(val year : String) : CarRegEvents()


    data class OnModelChange(val text: String) : CarRegEvents()
    data class OnModelSelect(val text: String) : CarRegEvents()


    data class OnPriceChange(val price: String) : CarRegEvents()

    data class OnChassisNoChange(val no: String) : CarRegEvents()
    data class OnEngineNoChange(val no: String) : CarRegEvents()

    data class OnSelectFuelType(val type: FuelTypes) : CarRegEvents()

    data class OnSelectColor(val color: Color) : CarRegEvents()

    data class OnRegister(
        val onSuccess: (msg: String) -> Unit,
        val onFailed: (error: String) -> Unit
    ) : CarRegEvents()
}
