package com.example.carDock.domain.util

import com.example.carDock.domain.model.BaseUser

sealed class UserAuthResult {
    object Failed : UserAuthResult()
    class Success(val user: BaseUser) : UserAuthResult()
}
