package com.example.carDock.presentation.dashBoard.fragments.carList


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.use_case.BrandUseCases
import com.example.carDock.domain.use_case.CarUseCases
import com.example.carDock.domain.util.filterUtil.CarListFilter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CarListViewModel : ViewModel() {

    private var _state = mutableStateOf(CarListState())
    var state: State<CarListState> = _state


    private var _filterState = mutableStateOf(FilterState())
    var filterState: State<FilterState> = _filterState


    private var carsJob: Job? = null

    init {
        getCars()
        _filterState.value.brandList = BrandUseCases.getBrands()?.map { it.brand } ?: emptyList()
    }


    fun onEvent(event : CarListEvents)
    {
        when(event)
        {
            /////////
            is CarListEvents.OnModelChange -> {
                _filterState.value = _filterState.value.copy(
                    filteredModel = event.model
                )

            }

            /////////
            is CarListEvents.OnBrandChange -> {
                _filterState.value = BrandUseCases.filterModelsByName(event.brand , "")?.let {
                    _filterState.value.copy(
                        modelList = it
                    )
                }!!

                _filterState.value = _filterState.value.copy(
                    filteredBrand = event.brand
                )


            }

            /////////
            is CarListEvents.OnFuelTypeChange -> {
                _filterState.value = _filterState.value.copy(
                    filteredFuelType = event.type
                )
            }

            /////////
            is CarListEvents.OnLowerBoundPriceChange -> {
                try {
                    _filterState.value = _filterState.value.copy(
                        lowerPrice = event.price.toLong()
                    )
                }finally {

                }


            }

            /////////
            CarListEvents.OnSoldFilter -> {
                try {
                    _filterState.value = _filterState.value.copy(
                        NoSold = !_filterState.value.NoSold
                    )
                }finally {

                }

            }

            /////////
            is CarListEvents.OnUpperBoundPriceChange -> {
                try {
                    _filterState.value = _filterState.value.copy(
                        upperPrice = event.price.toLong()
                    )
                }finally {

                }
            }




            /////////
            CarListEvents.Toggled -> {
                _state.value = _state.value.copy(
                    Toggled = !_state.value.Toggled
                )
            }

            ////////
            CarListEvents.OnFilterClear -> {
                _filterState.value = FilterState()
            }
        }

        getCars()
    }

    private fun updateFilter(filter : CarListFilter)
    {

    }




    private fun getCars() {
        var filterList : List<CarListFilter> = emptyList()
        val filterStateL = _filterState.value
        filterStateL.filteredBrand?.let {  filterList = filterList + CarListFilter.ByBrand(it)}
        filterStateL.filteredModel?.let {  filterList = filterList + CarListFilter.ByModel(it)}
        filterStateL.filteredFuelType?.let {  filterList = filterList + CarListFilter.ByFuelType(it)}
        filterList = filterList + CarListFilter.ByPriceRange(lb = filterStateL.lowerPrice, ub = filterStateL.upperPrice)

        if(filterStateL.NoSold)
            filterList = filterList + CarListFilter.NoSoldCars





        carsJob?.cancel()
        carsJob = viewModelScope.launch {
            CarUseCases.getAllCars(
                filters = filterList ,
                order = _filterState.value.sortOrder
            ).collect {
                _state.value = _state.value.copy(
                    carList = it
                )
            }


        }

    }

}