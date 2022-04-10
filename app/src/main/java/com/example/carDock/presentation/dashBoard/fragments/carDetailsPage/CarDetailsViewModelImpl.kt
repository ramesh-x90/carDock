package com.example.carDock.presentation.dashBoard.fragments.carDetailsPage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.use_case.CarUseCases
import com.example.carDock.domain.use_case.UserUseCases
import com.example.carDock.domain.util.CarBuyResults
import kotlinx.coroutines.launch

class CarDetailsViewModelImpl : ViewModel(){

    var state = mutableStateOf(
        DummyCar()
    )

    fun onEvent(event : CarPurchaseEvents)
    {
        val action = when(event)
        {
            is CarPurchaseEvents.OnBuy -> {
                purchaseCar(event)
            }
        }
    }

    private fun purchaseCar(event: CarPurchaseEvents.OnBuy)
    {
        viewModelScope.launch {
            val cation = when(val res = UserUseCases.buyACar(state.value.id))
            {
                is CarBuyResults.Failed -> when(res)
                {
                    CarBuyResults.Failed.CarIsAlreadySold -> {
                        event.onFailed(res.error)
                    }
                    CarBuyResults.Failed.InvalidCar -> {
                        event.onFailed(res.error)
                    }
                    CarBuyResults.Failed.InvalidUserCredential -> {
                        event.onFailed(res.error)
                    }
                    CarBuyResults.Failed.NotEnoughBalance -> {
                        event.onFailed(res.error)
                    }
                }

                CarBuyResults.Success -> {
                    getCar(state.value.id)
                    event.onSuccess("Purchased Done")
                }
            }
        }
    }

    fun getCar(id : Long)
    {
        viewModelScope.launch {
            val car : Car? = CarUseCases.getCarById(id)

            if (car != null) {
                val seller = UserUseCases.getUserById(car.seller)
                state.value = state.value.copy(
                    brand = car.brand,
                    engine_no = car.engine_no,
                    chassis_no = car.chassis_no,
                    fuelType = car.fuelType,
                    model = car.model,
                    availability = car.availability,
                    Color = car.Color,
                    seller = seller!!.name,
                    timestamp = car.timestamp,
                    price = car.price,
                    id = car.id!!
                )
            }
        }
    }
}