package com.example.carDock.presentation.dashBoard.fragments.carReg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.debug.Log
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.use_case.BrandUseCases
import com.example.carDock.domain.use_case.CarUseCases
import com.example.carDock.domain.util.CarRegErrors
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CarRegViewModelImpl : ViewModel(), CarRegViewModel {

    init {
        Log("regViewModel Called")
    }

    private var _carRegFormState = mutableStateOf(
        CarRegState()
    )

    override var carRegFormState: State<CarRegState> = _carRegFormState


    private var _formErrorState = mutableStateOf(
        CarRegErrorState()
    )

    override var formErrorState: State<CarRegErrorState> = _formErrorState

    private var _coroutineLunched = false


    override fun onEvent(event: CarRegEvents) {
        _formErrorState.value = CarRegErrorState()

        val action = when (event) {
            is CarRegEvents.OnBrandChange -> {

                _carRegFormState.value = _carRegFormState.value.copy(
                    selectedBrand = event.text
                )

                _carRegFormState.value = _carRegFormState.value.copy(
                    brandList = BrandUseCases.getBrandsByBrandName(event.text),
                )

                _carRegFormState.value.brandList?.let {
                    if (it.size == 1) {
                        _carRegFormState.value = _carRegFormState.value.copy(
                            selectedBrand = it[0]
                        )
                    } else {
                        _carRegFormState.value = _carRegFormState.value.copy(
                            selectedBrand = ""
                        )
                    }
                }
            }

            is CarRegEvents.OnModelChange -> {

                if (_carRegFormState.value.selectedBrand.isBlank()) {
                    _carRegFormState.value = _carRegFormState.value.copy(
                        modelsList = emptyList()
                    )
                    return
                }

                _carRegFormState.value = _carRegFormState.value.copy(
                    modelsList = BrandUseCases.filterModelsByName(
                        brand = _carRegFormState.value.selectedBrand,
                        name = event.text
                    )
                )


            }


            is CarRegEvents.OnBrandSelect -> {

                _carRegFormState.value = _carRegFormState.value.copy(
                    selectedBrand = event.brand
                )

            }

            is CarRegEvents.OnModelSelect -> {
                _carRegFormState.value = _carRegFormState.value.copy(
                    selectedModel = event.text
                )
            }

            is CarRegEvents.OnSelectFuelType -> {
                _carRegFormState.value = _carRegFormState.value.copy(
                    fuelType = event.type.name
                )
            }

            is CarRegEvents.OnPriceChange -> {

                if (event.price.isBlank()) {
                    _carRegFormState.value = _carRegFormState.value.copy(
                        price = -1
                    )
                    return
                }

                event.price.isDigitsOnly().let {
                    when (it) {
                        true -> {
                            try {
                                _carRegFormState.value = _carRegFormState.value.copy(
                                    price = event.price.toLong()
                                )
                            }catch (e : Exception)
                            {

                            }

                        }
                    }
                }

            }
            is CarRegEvents.OnChassisNoChange -> {
                _carRegFormState.value = _carRegFormState.value.copy(
                    chassis_no = event.no
                )
            }
            is CarRegEvents.OnEngineNoChange -> {
                _carRegFormState.value = _carRegFormState.value.copy(
                    engine_no = event.no
                )
            }


            is CarRegEvents.OnSelectColor -> {
                _carRegFormState.value = _carRegFormState.value.copy(
                    selectedColor = event.color
                )
            }

            is CarRegEvents.OnYearChange -> {
                _carRegFormState.value = _carRegFormState.value.copy(
                    year = event.year
                )
            }


            is CarRegEvents.OnRegister -> registerCar(event)
        }
    }


    private fun registerCar(event: CarRegEvents.OnRegister) {
        val carRegStateObj = _carRegFormState.value

        val car = Car(
            seller = carRegStateObj.seller,
            model = carRegStateObj.selectedModel,
            fuelType = carRegStateObj.fuelType,
            engine_no = carRegStateObj.engine_no,
            Color = carRegStateObj.selectedColor.toArgb(),
            chassis_no = carRegStateObj.chassis_no,
            brand = carRegStateObj.selectedBrand,
            price = carRegStateObj.price,
            launchedYear = carRegStateObj.year
        )

        viewModelScope.launch {

            if (_coroutineLunched) cancel()

            _coroutineLunched = true

            val action = when (val res = CarUseCases.registerCar(car)) {
                CarRegErrors.InvalidBrandError -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        brandError = true
                    )
                }
                CarRegErrors.InvalidChassisNoError -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        chassisNoError = true
                    )
                }
                CarRegErrors.InvalidEngineNoError -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        engineNoError = true
                    )
                }
                CarRegErrors.InvalidFuelTypeError -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        fuelTypeError = true
                    )
                }
                CarRegErrors.InvalidModelError -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        modelError = true
                    )
                }
                CarRegErrors.InvalidPriceError -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        priceError = true
                    )
                }



                CarRegErrors.InvalidLaunchYear -> {
                    event.onFailed(res.error)
                    _formErrorState.value = _formErrorState.value.copy(
                        yearError = true
                    )
                    _carRegFormState.value = _carRegFormState.value.copy(
                        year = ""
                    )
                }


                CarRegErrors.InvalidSellerError -> {
                    event.onFailed(res.error)
                }


                CarRegErrors.None -> {
                    event.onSuccess("Registration successful")
                    clearState()

                }
            }
            _coroutineLunched = false
            cancel()

        }

    }


    private fun clearState() {
        _carRegFormState.value = CarRegState()
    }

}