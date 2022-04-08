package com.example.carDock.serviceLocators


import com.example.carDock.data.data_source.CarDockAppDataBase
import com.example.carDock.data.data_source.UserDao

object DataBaseServiceLocator {

    private val db = CarDockAppDataBase.getInstance(com.example.carDock.AppModule.instance)

    fun getCarDao() = db.carDao()
    fun getBrandDb(): com.example.carDock.data.data_source.BrandDataSource =
        com.example.carDock.data.data_source.BrandDataSource(com.example.carDock.AppModule.brandJsonFilePath)
    fun getUserDb(): UserDao = db.userDao()
}