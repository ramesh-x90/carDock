package com.example.carDock.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.use_case.UserUseCases
import com.example.carDock.domain.util.UserAuthResult
import kotlinx.coroutines.launch

class LoginViewModelImpl : ViewModel() , LoginViewModel {
    override val loginViewState = mutableStateOf(
        LoginState()
    )
    override val loginErrorState: State<LoginErrorState> = mutableStateOf(
        LoginErrorState()
    )

    override fun onEvent(event: LoginEvents) {
        val action = when(event)
        {
            is LoginEvents.OnEmailChange -> {
                loginViewState.value = loginViewState.value.copy(
                    email = event.data
                )
            }
            is LoginEvents.OnPasswordChange -> {
                loginViewState.value = loginViewState.value.copy(
                    password = event.data
                )
            }


            is LoginEvents.OnLoginSubmit -> {
                login(event)
            }
        }
    }

    private fun login(event: LoginEvents.OnLoginSubmit) {
        viewModelScope.launch {
            val res = UserUseCases.login(
                email = loginViewState.value.email ,
                password = loginViewState.value.password
            )

            val action = when(res)
            {
                UserAuthResult.Failed -> {
                    event.onFailed("Login Failed")
                }
                is UserAuthResult.Success -> {
                    res.user
                    event.onSuccess("Login Successful")
                }
            }



        }
    }
}