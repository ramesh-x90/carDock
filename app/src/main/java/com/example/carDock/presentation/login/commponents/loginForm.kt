package com.example.carDock.presentation.login.commponents


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.carDock.R
import com.example.carDock.presentation.core.compponents.CustomTextField
import com.example.carDock.presentation.login.LoginEvents
import com.example.carDock.presentation.login.LoginViewModel
import com.example.carDock.ui.theme.MyColors


@Composable
fun LoginForm(viewModel: LoginViewModel, onSuccess: (s : String) -> Unit, onFailed: (s : String) -> Unit) {
    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            // Username field

            //Email Field
            CustomTextField(
                fieldValue = viewModel.loginViewState.value.email ,
                label = "Email",
                isError = viewModel.loginErrorState.value.emailError,
                onChange = {
                    viewModel.onEvent(LoginEvents.OnEmailChange(it))
                },
                leadingIcon = R.drawable.ic_baseline_person_24,
                trailingIcon = R.drawable.ic_baseline_privacy_tip_24
            )


            //Password field

            CustomTextField(
                fieldValue = viewModel.loginViewState.value.password ,
                label = "Password",
                isError = viewModel.loginErrorState.value.passwordError,
                onChange = {
                    viewModel.onEvent(LoginEvents.OnPasswordChange(it))
                },
                isPasswordField = true,
            )


            // buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                val btnModifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(0.3f)
                    .shadow(elevation = 10.dp)

                val btnColors = ButtonDefaults.buttonColors(
                    backgroundColor = MyColors.primary,
                    contentColor = MyColors.primaryText
                )

                //login button
                Button(

                    modifier = btnModifier,
                    colors = btnColors,
                    onClick = {
                        viewModel.onEvent(LoginEvents.OnLoginSubmit(
                            onSuccess = onSuccess,
                            onFailed = onFailed
                        ))

                    }
                ) {
                    Text(text = "Login")
                }


            }


        }
    }
}
