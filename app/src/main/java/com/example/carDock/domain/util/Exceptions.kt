package com.example.carDock.domain.util

import java.lang.Exception

class NotEnoughBalanceException(val error : String) : Exception(error)

class InvalidCarException(val error : String) : Exception(error)

class SoldCarException(val error : String) : Exception(error)