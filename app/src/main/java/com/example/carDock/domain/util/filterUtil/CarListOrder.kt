package com.example.carDock.domain.util.filterUtil

sealed class CarListOrder
{
    data class ByDate( var order : OrderType ) : CarListOrder()

    data class ByPrice( var order : OrderType ) : CarListOrder()

}
