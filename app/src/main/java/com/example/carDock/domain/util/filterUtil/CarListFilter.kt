package com.example.carDock.domain.util.filterUtil

sealed class CarListFilter {
    data class ByBrand(val brandName : String) : CarListFilter()
    data class ByModel(val modelName : String) : CarListFilter()
    data class ByPriceRange(val lb : Long , val ub : Long) : CarListFilter()
    data class ByFuelType(val fuelType : String) : CarListFilter()
    object NoSoldCars : CarListFilter()
}