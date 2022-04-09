package com.example.carDock.domain.use_case



import com.example.carDock.domain.model.Car
import com.example.carDock.domain.util.CarRegErrors
import com.example.carDock.domain.util.extentions.validate
import com.example.carDock.globalState.CurrentUserState
import kotlinx.coroutines.flow.Flow

object CarUseCases {

    private val repository = com.example.carDock.AppModule.getDSRepoServiceLocator().getCarRepositoryImpl()

    fun getSellingCars(): Flow<List<Car>> = repository.getSellingCars(true)


    suspend fun registerCar(car: Car): CarRegErrors {
        val signedCar = addSellerId(car)
        val validationResult = signedCar.validate()

        return when (validationResult.res) {
            CarRegErrors.None -> {
                repository.insertCar(
                    signedCar
                )
                CarRegErrors.None
            }

            else -> validationResult.res
        }


    }

    fun getAllCars(): Flow<List<Car>> = repository.getCars()

    suspend fun getCarById(id: Long): Car? = repository.getCarById(id)

    private fun addSellerId(car : Car ) : Car
    {
        val sellerId= CurrentUserState.getCurrentUser()?.id

        if(sellerId != null) {
            return car.copy(
                seller = sellerId
            )
        }
        return car

    }

}