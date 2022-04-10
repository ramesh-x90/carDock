package com.example.carDock.serviceLocators


import com.example.carDock.presentation.dashBoard.fragments.carList.CarListViewModel
import com.example.carDock.presentation.dashBoard.fragments.carDetailsPage.CarDetailsViewModelImpl
import com.example.carDock.presentation.dashBoard.fragments.carReg.CarRegViewModelImpl
import com.example.carDock.presentation.dashBoard.fragments.profile.ProfileViewModel
import com.example.carDock.presentation.login.LoginViewModel
import com.example.carDock.presentation.login.LoginViewModelImpl
import com.example.carDock.presentation.userRegistration.UserRegistrationViewModel
import com.example.carDock.presentation.userRegistration.UserRegistrationViewModelImpl


object ViewModelsServiceLocator {

    private var carListViewModel: CarListViewModel? = null
    private var carRegScreenViewModel: CarRegViewModelImpl? = null
    private var userRegScreenViewModel: UserRegistrationViewModel? = null
    private var userLoginScreenViewModel: LoginViewModel? = null
    private var carDetailsScreenViewModel: CarDetailsViewModelImpl? = null
    private var profileViewModel: ProfileViewModel? = null

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
    fun getUserLoginScreenViewModel() =
        userLoginScreenViewModel ?: LoginViewModelImpl().also {
            userLoginScreenViewModel = it
        }

    fun getCarDetailsViewModel() =
        carDetailsScreenViewModel ?: CarDetailsViewModelImpl().also {
            carDetailsScreenViewModel = it
    }

    fun getProfileViewModel() =
        profileViewModel ?: ProfileViewModel().also {
            profileViewModel = it
        }


    fun resetViewModels() {
        carListViewModel = CarListViewModel()
        carRegScreenViewModel = CarRegViewModelImpl()
        userRegScreenViewModel = UserRegistrationViewModelImpl()
        userLoginScreenViewModel = LoginViewModelImpl()
        carDetailsScreenViewModel = CarDetailsViewModelImpl()
        profileViewModel = ProfileViewModel()
    }


}