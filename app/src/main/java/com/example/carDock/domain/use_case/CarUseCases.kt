package com.example.carDock.domain.use_case


import com.example.carDock.debug.Log
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.util.CarRegErrors
import com.example.carDock.domain.util.InvalidCarException
import com.example.carDock.domain.util.SoldCarException
import com.example.carDock.domain.util.extentions.validate
import com.example.carDock.domain.util.filterUtil.CarListFilter
import com.example.carDock.domain.util.filterUtil.CarListOrder
import com.example.carDock.domain.util.filterUtil.OrderType
import com.example.carDock.globalState.CurrentUserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object CarUseCases {

    private val repository =
        com.example.carDock.AppModule.getDSRepoServiceLocator().getCarRepositoryImpl()

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

    fun getAllCars(
        filters: List<CarListFilter>,
        order: CarListOrder = CarListOrder.ByPrice(order = OrderType.Ascending)
    ): Flow<List<Car>> {
        return repository.getCars()
            .map {
                when (order) {
                    is CarListOrder.ByDate -> {
                        when (order.order) {
                            OrderType.Ascending -> {
                                it.sortedBy { car -> car.timestamp }
                            }
                            OrderType.Descending -> {
                                it.sortedByDescending { car -> car.timestamp }

                            }
                        }
                    }
                    is CarListOrder.ByPrice -> {
                        when (order.order) {
                            OrderType.Ascending -> {
                                it.sortedBy { car -> car.price }
                            }
                            OrderType.Descending -> {
                                it.sortedByDescending { car -> car.price }

                            }
                        }
                    }
                }.filter { car ->
                    var bool = true

                    filters.forEach { filter ->
                        if (bool) {
                            bool = when (filter) {
                                is CarListFilter.ByBrand -> {
                                    (car.brand.lowercase() == filter.brandName.lowercase())
                                }
                                is CarListFilter.ByFuelType -> {
                                    (car.fuelType.lowercase() == filter.fuelType.lowercase())
                                }
                                is CarListFilter.ByModel -> {
                                    (car.model.lowercase() == filter.modelName.lowercase())
                                }
                                is CarListFilter.ByPriceRange -> {
                                    (car.price <= filter.ub && car.price >= filter.lb)
                                }
                                CarListFilter.NoSoldCars -> {
                                    car.availability
                                }
                            }
                        } else return@forEach

                    }
                    bool
                }
            }
    }

    suspend fun getCarById(id: Long): Car? = repository.getCarById(id)

    private fun addSellerId(car: Car): Car {
        val sellerId = CurrentUserState.getCurrentUser()?.id

        return sellerId?.let {
            car.copy(
                seller = sellerId
            )
        } ?: car

    }


    @Throws(exceptionClasses = [SoldCarException::class, InvalidCarException::class])
    suspend fun buyACar(id: Long) {
        val car = getCarById(id)

        car?.let {
            if (car.availability) {
                repository.makeCarSold(id)
            } else throw SoldCarException("sold car")
        } ?: throw InvalidCarException("invalid car")

    }

    suspend fun isCarAvailable(id: Long) = repository.isCarAvailable(id)

}