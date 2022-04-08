package com.example.carDock.presentation.dashBoard.fragments.carList


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.use_case.CarUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CarListViewModel : ViewModel() {

    private var _state = mutableStateOf(CarListState())
    var state: State<CarListState> = _state


    private var carsJob: Job? = null

    init {
        getCars()
    }

    private fun getCars() {
        carsJob?.cancel()
        carsJob = viewModelScope.launch {
            CarUseCases.getSellingCars().collect {
                _state.value = CarListState(carList = it)
            }


        }

    }

}