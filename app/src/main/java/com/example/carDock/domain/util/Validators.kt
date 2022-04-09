package com.example.carDock.domain.util

object Validators {

    private const val emailPattern = """^[\w]+@[\w]+\.[\w]{2,4}$"""
    private const val contactNumPattern = """^[\d]{9,10}$"""

    fun validateEmail(str: String): Boolean = str.contains(Regex(emailPattern))
    fun validateContactNum(str: String) = str.contains(Regex(contactNumPattern))
}