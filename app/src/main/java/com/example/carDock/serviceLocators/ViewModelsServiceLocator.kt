package com.example.carDock.serviceLocators


import com.example.carDock.presentation.dashBoard.fragments.carList.CarListViewModel
import com.example.carDock.presentation.dashBoard.fragments.carReg.CarRegViewModelImpl
import com.example.carDock.presentation.userRegistration.UserRegistrationViewModel
import com.example.carDock.presentation.userRegistration.UserRegistrationViewModelImpl


object ViewModelsServiceLocator {

    private var carListViewModel: CarListViewModel? = null
    private var carRegScreenViewModel: CarRegViewModelImpl? = null
    private var userRegScreenViewModel: UserRegistrationViewModel? = null

    fun getCarListScreenViewModel() = carListViewModel ?: CarListViewModel().also {
        carListViewModel = it
    }

    fun getCarRegScreenViewModel() = carRegScreenViewModel ?: CarRegViewModelImpl().also {
        carRegScreenViewModel = it
    }

    fun getUserRegScreenViewModel() =
        userRegScreenViewModel ?: UserRegistrationViewModelImpl().also {
            userRegScreenViewModel = it
        }


    fun resetViewModels() {
        carListViewModel = CarListViewModel()
        carRegScreenViewModel = CarRegViewModelImpl()
        userRegScreenViewModel = UserRegistrationViewModelImpl()
    }


}