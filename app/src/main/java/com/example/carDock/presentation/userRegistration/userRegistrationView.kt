package com.example.carDock.presentation.userRegistration


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carDock.presentation.core.compponents.MyToast
import com.example.carDock.presentation.dashBoard.commponents.core.PageHeader
import com.example.carDock.presentation.userRegistration.commponents.UserRegPwdField
import com.example.carDock.presentation.userRegistration.commponents.UserRegTextField
import com.example.carDock.ui.theme.MyColors

@Composable
fun UserRegistrationView(navController: NavHostController) {
    val viewModel =
        com.example.carDock.AppModule.getViewModelServiceLocator().getUserRegScreenViewModel()

    Surface(color = MyColors.primaryLight, modifier = Modifier.fillMaxSize()) {
        if (viewModel.userRegState.value.loading) {
            Loading()
        } else {
            UserRegForm(navController , viewModel)
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserRegForm(navController: NavHostController, viewModel: UserRegistrationViewModel) {

    val btnColors = ButtonDefaults.buttonColors(
        backgroundColor = MyColors.primary,
        contentColor = MyColors.primaryText
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        stickyHeader {
            PageHeader(
                bgColor = MyColors.primary,
                header = "User Registration"
            )
            
        }
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
                },
                colors = btnColors
            ) {
                Text(text = "Submit")
            }
        }

        //back
        item {
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    navController.popBackStack()
                },
                colors = btnColors
            ) {
                Text(text = "Back")
            }
        }
    }
}