package com.example.carDock.presentation.dashBoard.fragments.carList

import com.example.carDock.domain.model.Brand
import com.example.carDock.domain.use_case.BrandUseCases
import com.example.carDock.domain.util.FuelTypes
import com.example.carDock.domain.util.filterUtil.CarListFilter
import com.example.carDock.domain.util.filterUtil.CarListOrder
import com.example.carDock.domain.util.filterUtil.OrderType

data class FilterState(
    val lowerPrice: Long = 0,
    val upperPrice: Long = 5000000L,
    val filteredBrand: String ?= null,
    val filteredModel: String ?= null,
    val filteredFuelType: String ?= null,

    val sortOrder: CarListOrder = CarListOrder.ByPrice(OrderType.Ascending),
    val orderType : OrderType = OrderType.Ascending,

    var brandList: List<String>? = BrandUseCases.getBrands()?.map { it.brand },
    val modelList: List<String> = emptyList(),
    val fuelTypes: List<FuelTypes> = FuelTypes.getListOfFuelTypes(),

    val NoSold : Boolean = true,

    )