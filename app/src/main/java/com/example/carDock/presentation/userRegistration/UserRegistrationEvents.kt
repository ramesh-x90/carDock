package com.example.carDock.presentation.userRegistration

sealed class UserRegistrationEvents {
    data class OnUserNameChange(val data: String) : UserRegistrationEvents()
    data class OneEmailChange(val data: String) : UserRegistrationEvents()
    data class OnPasswordChange(val data: String) : UserRegistrationEvents()
    data class OnPasswordConfirmChange(val data: String) : UserRegistrationEvents()
    data class OnAddressChange(val data: String) : UserRegistrationEvents()
    data class OnContactNoChange(val data: String) : UserRegistrationEvents()
    data class OnFormSubmit(
        val onSuccess: (str: String) -> Unit,
        val onFailed: (str: String) -> Unit
    ) : UserRegistrationEvents()
}