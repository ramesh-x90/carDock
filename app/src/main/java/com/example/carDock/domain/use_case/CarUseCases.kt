package com.example.carDock.domain.use_case



import com.example.carDock.domain.model.Car
import com.example.carDock.domain.util.CarRegErrors
import com.example.carDock.domain.util.extentions.validate
import kotlinx.coroutines.flow.Flow

object CarUseCases {

    private val repository = com.example.carDock.AppModule.getDSRepoServiceLocator().getCarRepositoryImpl()

    fun getSellingCars(): Flow<List<Car>> = repository.getSellingCars(true)


    suspend fun registerCar(car: Car): CarRegErrors {
        val validationResult = car.validate()

        return when (validationResult.res) {
            CarRegErrors.None -> {
                repository.insertCar(
                    car
                )
                CarRegErrors.None
            }

            else -> validationResult.res
        }


    }

    fun getAllCars(): Flow<List<Car>> = repository.getCars()

    suspend fun getCarById(id: Long): Car? = repository.getCarById(id)

}