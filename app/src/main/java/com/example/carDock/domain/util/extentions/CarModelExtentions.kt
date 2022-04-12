package com.example.carDock.domain.util.extentions


import com.example.carDock.domain.model.Car
import com.example.carDock.domain.use_case.BrandUseCases
import com.example.carDock.domain.util.CarRegErrors
import com.example.carDock.domain.util.CarRegValidationResult
import com.example.carDock.domain.util.Validators.isValidFuelType
import com.example.carDock.domain.util.Validators.isValidYear


fun Car.validate(): CarRegValidationResult {

    return when {
        brand.isBlank().or(
            BrandUseCases.isNotBrandExist(brand)
        ) -> CarRegValidationResult(this, CarRegErrors.InvalidBrandError)

        launchedYear.isBlank() || !isValidYear(launchedYear) -> CarRegValidationResult(this , CarRegErrors.InvalidLaunchYear)

        model.isBlank().or(
            BrandUseCases.isNotModelExist(brand, model)
        ) -> CarRegValidationResult(this, CarRegErrors.InvalidModelError)

        (price < 0) -> CarRegValidationResult(this, CarRegErrors.InvalidPriceError)

        chassis_no.isBlank() -> CarRegValidationResult(this, CarRegErrors.InvalidChassisNoError)

        engine_no.isBlank() -> CarRegValidationResult(this, CarRegErrors.InvalidEngineNoError)

        (seller < 0) -> CarRegValidationResult(this, CarRegErrors.InvalidSellerError)

        !isValidFuelType(fuelType) -> CarRegValidationResult(
            this,
            CarRegErrors.InvalidFuelTypeError
        )



        else -> CarRegValidationResult(this, CarRegErrors.None)

    }

}


