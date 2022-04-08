package com.example.carDock.serviceLocators

import com.example.carDock.data.repository.BrandRepositoryImpl
import com.example.carDock.data.repository.CarRepositoryImpl
import com.example.carDock.data.repository.UserRepositoryImpl
import com.example.carDock.domain.repository.BrandRepository
import com.example.carDock.domain.repository.CarRepository
import com.example.carDock.domain.repository.UserRepository

object DataSourceRepoServiceLocator {
    private var carRepo: CarRepository? = null
    private var brandRepo: BrandRepository? = null
    private var userRepo: UserRepository? = null

    fun getCarRepositoryImpl(): CarRepository =
        carRepo ?: CarRepositoryImpl(DataBaseServiceLocator.getCarDao()).also { carRepo = it }

    fun getBrandRepositoryImpl(): BrandRepository = brandRepo ?: BrandRepositoryImpl(
        com.example.carDock.AppModule.getDSServiceLocator().getBrandDb()
    ).also { brandRepo = it }

    fun getUserRepositoryImpl(): UserRepository = userRepo ?: UserRepositoryImpl(
        com.example.carDock.AppModule.getDSServiceLocator().getUserDb()
    ).also { userRepo = it }
}