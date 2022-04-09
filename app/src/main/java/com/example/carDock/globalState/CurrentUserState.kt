package com.example.carDock.globalState

import com.example.carDock.domain.model.BaseUser

class CurrentUserState(val user : BaseUser) {

    init {
        currentUser = user
    }

    companion object{
        private var currentUser: BaseUser? = null

        fun getCurrentUser() : BaseUser ? = currentUser
    }
}