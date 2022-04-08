package com.example.carDock.presentation.userRegistration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.debug.Log
import com.example.carDock.domain.model.User
import com.example.carDock.domain.use_case.UserUseCases
import com.example.carDock.domain.util.UserRegResult
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserRegistrationViewModelImpl : ViewModel(), UserRegistrationViewModel {

    override val userRegState = mutableStateOf(
        UserRegistrationState()
    )

    override val userRegErrorState = mutableStateOf(
        UserRegErrorState()
    )

    private var _coroutineLunched = false

    override fun onEvent(event: UserRegistrationEvents) {

        userRegErrorState.value = UserRegErrorState()

        val action = when (event) {
            is UserRegistrationEvents.OnAddressChange -> {
                userRegState.value = userRegState.value.copy(
                    address = event.data
                )
            }
            is UserRegistrationEvents.OnContactNoChange -> {
                userRegState.value = userRegState.value.copy(
                    contact_number = event.data
                )
            }
            is UserRegistrationEvents.OnPasswordChange -> {
                userRegState.value = userRegState.value.copy(
                    password = event.data
                )
            }
            is UserRegistrationEvents.OnPasswordConfirmChange -> {
                userRegState.value = userRegState.value.copy(
                    conformPassword = event.data
                )
            }
            is UserRegistrationEvents.OnUserNameChange -> {
                userRegState.value = userRegState.value.copy(
                    userName = event.data
                )
            }
            is UserRegistrationEvents.OneEmailChange -> {
                userRegState.value = userRegState.value.copy(
                    email = event.data
                )
            }
            is UserRegistrationEvents.OnFormSubmit -> {
                if (userRegState.value.password == userRegState.value.conformPassword) {
                    callUseCaseToRegisterUser(event)
                } else {
                    userRegErrorState.value = userRegErrorState.value.copy(
                        confirmPasswordError = true
                    )
                    event.onFailed("Password is mismatched")
                }

            }
        }
    }


    private fun callUseCaseToRegisterUser(event: UserRegistrationEvents.OnFormSubmit) {

        val user = createUserFromState()

        user ?: return

        viewModelScope.launch {

            userRegState.value = userRegState.value.copy(
                loading = true
            )

            if (_coroutineLunched) cancel()

            _coroutineLunched = true

            delay(2000)

            val regRes = UserUseCases.registerUser(
                user = user
            )

            checkUserResults(event, regRes)

            Log("ll")


            _coroutineLunched = false

            userRegState.value = userRegState.value.copy(
                loading = false
            )

            cancel()
        }

    }


    private fun createUserFromState(): User? {
        val state = userRegState.value

        return try {
            User(
                name = state.userName,
                email = state.email,
                address = state.address,
                password = state.password,
                contact_number = state.contact_number.toInt()
            )

        } catch (e: Exception) {
            null
        }

    }


    private fun checkUserResults(
        event: UserRegistrationEvents.OnFormSubmit,
        regRes: UserRegResult
    ) {
        when (regRes) {

            UserRegResult.Success -> {
                userRegState.value = UserRegistrationState()
                userRegErrorState.value = UserRegErrorState()
                event.onSuccess("done")
            }

            else -> {
                when (regRes as UserRegResult.Failed) {
                    is UserRegResult.Failed.DbError -> {
                        event.onFailed((regRes as UserRegResult.Failed.DbError).details)
                        userRegErrorState.value = userRegErrorState.value.copy(
                            emailError = true
                        )
                    }
                    UserRegResult.Failed.InvalidAddress -> {
                        event.onFailed(regRes.error)
                        userRegState.value = userRegState.value.copy(
                            address = ""
                        )
                        userRegErrorState.value = userRegErrorState.value.copy(
                            addressError = true
                        )
                    }
                    UserRegResult.Failed.InvalidContactNumber -> {
                        event.onFailed(regRes.error)
                        userRegState.value = userRegState.value.copy(
                            contact_number = ""
                        )
                        userRegErrorState.value = userRegErrorState.value.copy(
                            contactNoError = true
                        )
                    }
                    UserRegResult.Failed.InvalidEmail -> {
                        event.onFailed(regRes.error)
                        userRegState.value = userRegState.value.copy(
                            email = ""
                        )
                        userRegErrorState.value = userRegErrorState.value.copy(
                            emailError = true
                        )
                    }
                    UserRegResult.Failed.InvalidInitBalance -> {
                        event.onFailed(regRes.error)
                    }
                    UserRegResult.Failed.InvalidPassword -> {
                        event.onFailed(regRes.error)
                        userRegState.value = userRegState.value.copy(
                            password = ""
                        )
                        userRegErrorState.value = userRegErrorState.value.copy(
                            passwordError = true
                        )
                    }
                    UserRegResult.Failed.InvalidUserName -> {
                        event.onFailed(regRes.error)
                        userRegState.value = userRegState.value.copy(
                            userName = ""
                        )
                        userRegErrorState.value = userRegErrorState.value.copy(
                            userNameError = true
                        )
                    }
                }
            }
        }
    }
}