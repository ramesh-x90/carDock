package com.example.carDock.domain.util



import android.os.Build
import java.time.LocalDateTime
import java.util.Calendar


object Validators {

    private const val emailPattern = """^[\w]+@[\w]+\.[\w]{2,4}$"""
    private const val contactNumPattern = """^[\d]{9,10}$"""
    private const val yearPattern = """^[1,2][\d]{3}$"""

    fun validateEmail(str: String): Boolean = str.contains(Regex(emailPattern))
    fun validateContactNum(str: String) = str.contains(Regex(contactNumPattern))


    fun isValidFuelType(name: String): Boolean {
        return when {
            (name == FuelTypes.Gasoline.name) -> true
            (name == FuelTypes.CNG.name) -> true
            (name == FuelTypes.Diesel.name) -> true
            (name == FuelTypes.Hydrogen.name) -> true
            (name == FuelTypes.LPG.name) -> true
            (name == FuelTypes.Electric.name) -> true
            else -> false
        }


    }


    fun isValidYear(year : String): Boolean {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val currentYear = Calendar.getInstance().weekYear.toInt()

                if(year.toInt() in 1500..currentYear) return true
                return false



            } else {

                return year.contains(Regex(yearPattern))

            }

        }catch (e : Exception) {
            return false
        }

    }
}