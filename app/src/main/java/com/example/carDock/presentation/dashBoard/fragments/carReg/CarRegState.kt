package com.example.carDock.presentation.dashBoard.fragments.carReg


import androidx.compose.ui.graphics.Color
import com.example.carDock.domain.util.FuelTypes


data class CarRegState(
    val seller: Long = 1,
    val fuelType: String = "",
    val engine_no: String = "",
    val chassis_no: String = "",
    val category: String = "Car",
    val price: Long = -1,
    val year: String = "",

    val brandList: List<String>? = emptyList(),
    val modelsList: List<String>? = emptyList(),

    val selectedBrand: String = "",
    val selectedModel: String = "",

    val fuelTypes: List<FuelTypes> = listOf(
        FuelTypes.Gasoline,
        FuelTypes.Diesel,
        FuelTypes.CNG,
        FuelTypes.LPG,
        FuelTypes.Hydrogen,
        FuelTypes.Electric
    ),

    val selectedColor: Color = Color.Red

)
