package com.example.carDock.presentation.dashBoard.fragments.carList

sealed class CarListEvents {
    object Toggled  : CarListEvents()
    data class OnBrandChange(val brand : String) : CarListEvents()
    object OnBrandReset : CarListEvents()

    data class OnModelChange(val model : String) : CarListEvents()
    object OnModelRest : CarListEvents()

    data class OnFuelTypeChange(val type : String) : CarListEvents()
    object OnFuelTypeReset : CarListEvents()

    data class OnLowerBoundPriceChange(val price : String) : CarListEvents()
    data class OnUpperBoundPriceChange(val price : String) : CarListEvents()
    object OnSoldFilter : CarListEvents()
    object OnFilterClear : CarListEvents()


    object OnSortByDate : CarListEvents()
    object OnSortByPrice : CarListEvents()

    object OnOrderChange : CarListEvents()
}