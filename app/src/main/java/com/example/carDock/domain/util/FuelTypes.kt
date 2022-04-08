package com.example.carDock.domain.util

sealed class FuelTypes(val name: String) {
    object Gasoline : FuelTypes("Gasoline")
    object Diesel : FuelTypes("Diesel")
    object CNG : FuelTypes("Compressed Natural Gas")
    object LPG : FuelTypes("Liquefied Petroleum Gas")
    object Hydrogen : FuelTypes("Hydrogen")
}
