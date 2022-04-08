package com.example.carDock.data.repository

import com.example.carDock.domain.model.Brand
import com.example.carDock.domain.repository.BrandRepository


class BrandRepositoryImpl(private val db: com.example.carDock.data.data_source.BrandDataSource) :
    BrandRepository {

    override fun getBrands(): List<Brand>? = db.getBrandList()

    override fun getBrandsByName(name: String): List<String>? =
        getBrands()?.filter {
            it.brand.lowercase().contains(
                Regex("^${name.lowercase()}")
            )
        }?.map { it.brand }


    override fun getModels(brandName: String): List<String>? =
        getBrands()?.find { it.brand == brandName }?.models


}


