package com.example.carDock.presentation.dashBoard.fragments.carDetailsPage

sealed class CarPurchaseEvents {
    data class OnBuy(
        val onSuccess : (s : String) -> Unit ,
        val onFailed : (s : String ) -> Unit
    ) : CarPurchaseEvents()
}