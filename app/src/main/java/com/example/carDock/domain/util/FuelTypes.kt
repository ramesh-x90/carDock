package com.example.carDock.domain.util

sealed class FuelTypes(val name: String) {
    object Gasoline : FuelTypes("Gasoline")
    object Diesel : FuelTypes("Diesel")
    object CNG : FuelTypes("Compressed Natural Gas")
    object LPG : FuelTypes("Liquefied Petroleum Gas")
    object Hydrogen : FuelTypes("Hydrogen")
    object Electric : FuelTypes("Electric")


    companion object
    {
        fun getListOfFuelTypes(): List<FuelTypes> {
            return listOf(
                FuelTypes.Gasoline,
                FuelTypes.Diesel,
                FuelTypes.CNG,
                FuelTypes.LPG,
                FuelTypes.Hydrogen,
                FuelTypes.Electric,
            )
        }
    }

}
