package com.example.carDock


import android.annotation.SuppressLint
import android.content.Context
import com.example.carDock.data.repository.BrandRepositoryImpl
import com.example.carDock.data.repository.CarRepositoryImpl
import com.example.carDock.serviceLocators.DataBaseServiceLocator
import com.example.carDock.serviceLocators.DataSourceRepoServiceLocator
import com.example.carDock.serviceLocators.ViewModelsServiceLocator

@SuppressLint("StaticFieldLeak")
object AppModule {
    lateinit var instance: Context
    const val brandJsonFilePath: String = "Brands/dataList.json"

    private var carRepo: CarRepositoryImpl? = null
    private var brandRepo: BrandRepositoryImpl? = null

    fun setContext(app: Context) {
        com.example.carDock.AppModule.instance = app
    }

    fun getDSServiceLocator() = DataBaseServiceLocator

    fun getDSRepoServiceLocator() = DataSourceRepoServiceLocator

    fun getViewModelServiceLocator() = ViewModelsServiceLocator


}