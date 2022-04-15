package com.example.carDock.presentation.dashBoard.fragments.carList


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.use_case.BrandUseCases
import com.example.carDock.domain.use_case.CarUseCases
import com.example.carDock.domain.util.filterUtil.CarListFilter
import com.example.carDock.domain.util.filterUtil.CarListOrder
import com.example.carDock.domain.util.filterUtil.OrderType
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


            /////////brand filter events
            is CarListEvents.OnBrandChange -> {
                _filterState.value = BrandUseCases.filterModelsByName(event.brand , "")?.let {
                    _filterState.value.copy(
                        modelList = it
                    )
                }!!

                _filterState.value = _filterState.value.copy(
                    filteredBrand = event.brand
                )


                _filterState.value = _filterState.value.copy(
                    filteredModel = null
                )


            }


            ////////brand filter events
            CarListEvents.OnBrandReset -> {
                _filterState.value = _filterState.value.copy(
                    filteredBrand = null,
                    modelList = emptyList()
                )

            }



            /////////model filter events
            is CarListEvents.OnModelChange -> {
                _filterState.value = _filterState.value.copy(
                    filteredModel = event.model
                )

            }

            ////////model filter events
            CarListEvents.OnModelRest -> {
                _filterState.value = _filterState.value.copy(
                    filteredModel = null
                )

            }





            /////////FuelType filter events
            is CarListEvents.OnFuelTypeChange -> {
                _filterState.value = _filterState.value.copy(
                    filteredFuelType = event.type
                )
            }


            ////////FuelType filter events
            CarListEvents.OnFuelTypeReset -> {
                _filterState.value = _filterState.value.copy(
                    filteredFuelType = null
                )
            }






            /////////OnLowerBoundPriceChange filter events
            is CarListEvents.OnLowerBoundPriceChange -> {
                try {
                    _filterState.value = _filterState.value.copy(
                        lowerPrice = event.price.toLong()
                    )
                }catch (e : Exception){
                    _filterState.value = _filterState.value.copy(
                        lowerPrice = 0
                    )
                }


            }



            /////////OnUpperBoundPriceChange filter events
            is CarListEvents.OnUpperBoundPriceChange -> {
                try {
                    _filterState.value = _filterState.value.copy(
                        upperPrice = event.price.toLong()
                    )
                }catch (e : Exception){
                    _filterState.value = _filterState.value.copy(
                        upperPrice = 0
                    )
                }
            }





            /////////NoSold filter
            CarListEvents.OnSoldFilter -> {
                try {
                    _filterState.value = _filterState.value.copy(
                        NoSold = !_filterState.value.NoSold
                    )
                }catch (e : Exception){}
            }





            /////////filer toggle button
            CarListEvents.Toggled -> {
                _state.value = _state.value.copy(
                    Toggled = !_state.value.Toggled
                )
            }






            ////////clean filters
            CarListEvents.OnFilterClear -> {
                _filterState.value = FilterState()
            }




            //////// sorting
            CarListEvents.OnOrderChange -> {
                _filterState.value = _filterState.value.copy(
                    orderType = when(_filterState.value.orderType){
                        OrderType.Ascending -> OrderType.Descending
                        OrderType.Descending -> OrderType.Ascending
                    }
                )
            }


            /////
            CarListEvents.OnSortByDate -> {
                _filterState.value = _filterState.value.copy(
                    sortOrder = CarListOrder.ByDate(_filterState.value.orderType)
                )
            }
            CarListEvents.OnSortByPrice -> {
                _filterState.value = _filterState.value.copy(
                    sortOrder = CarListOrder.ByPrice(_filterState.value.orderType)
                )
            }
        }

        getCars()
    }

    private fun updateFilter(filter : CarListFilter)
    {

    }




    private fun getCars() {
        val filterList : MutableList<CarListFilter> = mutableListOf()
        val filterStateL = _filterState.value
        filterStateL.filteredBrand?.let {  filterList += CarListFilter.ByBrand(it)}
        filterStateL.filteredModel?.let {  filterList += CarListFilter.ByModel(it)}
        filterStateL.filteredFuelType?.let {  filterList += CarListFilter.ByFuelType(it)}
        filterList += CarListFilter.ByPriceRange(lb = filterStateL.lowerPrice, ub = filterStateL.upperPrice)

        if(filterStateL.NoSold)
            filterList += CarListFilter.NoSoldCars

        val order = when(val ord = filterStateL.sortOrder)
        {
            is CarListOrder.ByDate -> {
                ord.order = filterStateL.orderType
                ord
            }
            is CarListOrder.ByPrice -> {
                ord.order = filterStateL.orderType
                ord
            }
        }





        carsJob?.cancel()
        carsJob = viewModelScope.launch {
            CarUseCases.getAllCars(
                filters = filterList ,
                order = order
            ).collect {
                _state.value = _state.value.copy(
                    carList = it
                )
            }


        }

    }

}