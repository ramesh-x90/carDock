package com.example.carDock.domain.repository



import com.example.carDock.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    fun getCars(): Flow<List<Car>>

    suspend fun getCarById(id: Long): Car?

    suspend fun insertCar(car: Car)

    suspend fun deleteCar(car: Car)

    fun getSellingCars(bool: Boolean = true): Flow<List<Car>>
}