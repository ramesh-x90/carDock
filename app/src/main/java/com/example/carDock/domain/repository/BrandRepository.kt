package com.example.carDock.domain.repository

import com.example.carDock.domain.model.Brand

interface BrandRepository {

    fun getBrands(): List<Brand>?

    fun getBrandsByName(name: String): List<String>?

    fun getModels(brandName: String): List<String>?
}