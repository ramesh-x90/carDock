package com.example.carDock.domain.util.filterUtil

sealed class CarListOrder
{
    data class ByDate( val order : OrderType ) : CarListOrder()

    data class ByPrice( val order : OrderType ) : CarListOrder()

}
