package com.example.carDock.presentation.userRegistration


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carDock.presentation.core.compponents.MyToast
import com.example.carDock.presentation.userRegistration.commponents.UserRegPwdField
import com.example.carDock.presentation.userRegistration.commponents.UserRegTextField
import com.example.carDock.ui.theme.MyColors

@Composable
fun UserRegistrationView(navController: NavHostController) {
    val viewModel = com.example.carDock.AppModule.getViewModelServiceLocator().getUserRegScreenViewModel()

    Surface(color = MyColors.primaryLight, modifier = Modifier.fillMaxSize()) {
        if (viewModel.userRegState.value.loading) {
            Loading()
        } else {
            UserRegForm(viewModel)
        }

    }

}

@Composable
fun Loading() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
    {
        CircularProgressIndicator()
    }
}


@Composable
fun UserRegForm(viewModel: UserRegistrationViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        //user name
        item {
            UserRegTextField(
                value = viewModel.userRegState.value.userName,
                label = "Username",
                onChange = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OnUserNameChange(it)
                    )
                },
                isError = viewModel.userRegErrorState.value.userNameError

            )
        }

        //email
        item {
            UserRegTextField(
                value = viewModel.userRegState.value.email,
                label = "Email",
                onChange = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OneEmailChange(it)
                    )
                },
                isError = viewModel.userRegErrorState.value.emailError
            )
        }

        //address
        item {
            UserRegTextField(
                value = viewModel.userRegState.value.address,
                label = "Address",
                onChange = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OnAddressChange(it)
                    )
                },
                isError = viewModel.userRegErrorState.value.addressError
            )
        }

        //contact number
        item {
            UserRegTextField(
                value = viewModel.userRegState.value.contact_number,
                label = "Contact number",
                onChange = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OnContactNoChange(it)
                    )
                },
                isError = viewModel.userRegErrorState.value.contactNoError,
                keyBoardType = KeyboardType.Number
            )
        }

        //password
        item {
            UserRegPwdField(
                value = viewModel.userRegState.value.password,
                label = "Password",
                onChange = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OnPasswordChange(it)
                    )
                },
                isError = viewModel.userRegErrorState.value.passwordError

            )
        }

        //conformPassword
        item {
            UserRegPwdField(
                value = viewModel.userRegState.value.conformPassword,
                label = "Conform Password",
                onChange = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OnPasswordConfirmChange(it)
                    )
                },
                isError = viewModel.userRegErrorState.value.confirmPasswordError
            )
        }

        //Submit form
        item {
            Divider()
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    viewModel.onEvent(
                        UserRegistrationEvents.OnFormSubmit(onSuccess = {
                            MyToast(msg = it).show()
                        }, onFailed = {
                            MyToast(msg = it).show()
                        })
                    )
                }) {
                Text(text = "Submit")
            }
        }
    }
}