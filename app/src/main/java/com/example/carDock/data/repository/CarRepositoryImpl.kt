package com.example.carDock.data.repository


import com.example.carDock.data.data_source.CarDao
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow

class CarRepositoryImpl(private val dao: CarDao) : CarRepository {

    override fun getCars(): Flow<List<Car>> = dao.getCars()

    override fun getCarById(id: Long): Car? = dao.getCarById(id)

    override suspend fun insertCar(car: Car) = dao.addCar(car)

    override suspend fun deleteCar(car: Car) = dao.delCar(car)

    override fun getSellingCars(bool: Boolean): Flow<List<Car>> = dao.getSellingCars(bool)

}