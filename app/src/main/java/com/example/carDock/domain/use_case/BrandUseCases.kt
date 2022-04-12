package com.example.carDock.domain.use_case

import com.example.carDock.domain.model.Brand


object BrandUseCases {

    private val brandRepo = com.example.carDock.AppModule.getDSRepoServiceLocator().getBrandRepositoryImpl()

    fun getBrands(): List<Brand>? = brandRepo.getBrands()

    fun getBrandsByBrandName(name: String): List<String>? = brandRepo.getBrandsByName(name)

    private fun getBrandModels(brand: String): List<String>? = brandRepo.getModels(brand)

    fun filterModelsByName(brand: String, name: String): List<String>? =
        getBrandModels(brand)?.filter {
            it.lowercase().contains(
                Regex("^${name.lowercase()}")
            )
        }

    fun isBrandExist(brand: String): Boolean {

        return getBrandModels(brand)?.let {
            true } ?: false

    }

    fun isNotBrandExist(brand: String): Boolean = !isBrandExist(brand)

    fun isModelExist(brand: String, model: String): Boolean {
        getBrandModels(brand)?.forEach {
            if (model.lowercase() == it.lowercase()) {
                return true
            }
        }
        return false
    }

    fun isNotModelExist(brand: String, model: String): Boolean =
        !isModelExist(brand, model)
}