package com.example.carDock.data.data_source


import com.example.carDock.data.repository.BrandRepositoryImpl
import com.example.carDock.domain.model.Brand
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class BrandDataSource(private val path: String) {

    private var brandData: List<Brand>? = null

    init {
        brandData = readJsonFromAsset()

    }


    fun getBrandList(): List<Brand>? = brandData ?: this.readJsonFromAsset()

    private fun readJsonFromAsset(): List<Brand>? {

        var jsonStr: String? = null

        try {
            jsonStr = com.example.carDock.AppModule.instance.assets.open(path).bufferedReader().use {
                it.readText()
            }
        } catch (e: IOException) {
            throw JsonException("${BrandRepositoryImpl::class.simpleName}")
        }

        return deserializeObjectList(jsonStr)


    }

    private fun deserializeObjectList(str: String): List<Brand>? {

        val listBrandType = object : TypeToken<List<Brand>>() {}.type
        return Gson().fromJson(str, listBrandType)

    }

    class JsonException(private val msg: String) : Exception("JsonReadException: $msg")


}