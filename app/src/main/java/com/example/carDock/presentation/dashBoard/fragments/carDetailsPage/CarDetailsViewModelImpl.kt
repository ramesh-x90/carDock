package com.example.carDock.presentation.dashBoard.fragments.carDetailsPage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.use_case.CarUseCases
import kotlinx.coroutines.launch

class CarDetailsViewModelImpl : ViewModel(){

    var state = mutableStateOf(
        DummyCar()
    )

    fun getCar(id : Long)
    {
        viewModelScope.launch {
            val car : Car? = CarUseCases.getCarById(id)

            if (car != null) {
                state.value = state.value.copy(
                    brand = car.brand,
                    engine_no = car.engine_no,
                    chassis_no = car.chassis_no,
                    fuelType = car.fuelType,
                    model = car.model,
                    availability = car.availability,
                    Color = car.Color,
                    seller = car.seller,
                    timestamp = car.timestamp,
                    price = car.price,
                    id = car.id!!
                )
            }
        }
    }
}