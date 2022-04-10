package com.example.carDock.domain.util.filterUtil

sealed class OrderType
{
    object Ascending : OrderType()
    object Descending : OrderType()
}
