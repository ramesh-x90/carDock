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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.carDock.presentation.login.validator
import com.example.carDock.ui.theme.MyColors


@Composable
fun LoginForm(onSuccess: () -> Unit, onFailed: () -> Unit) {
    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            val textFieldModifier = Modifier.padding(bottom = 10.dp)


            // Username field
            val usernameState = remember {
                mutableStateOf("")
            }

            val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MyColors.primaryText,
                cursorColor = MyColors.secondaryLight,
                focusedBorderColor = MyColors.secondaryLight,
                focusedLabelColor = MyColors.secondaryLight,
                leadingIconColor = MyColors.secondaryLight,
                trailingIconColor = MyColors.secondaryLight


            )

            LoginFormUserNameField(
                fieldValue = usernameState.value,
                modifier = textFieldModifier,
                colors = textFieldColors,
                isError = !validator(usernameState.value),
                onChange = {
                    usernameState.value = it
                },
                validate = {
                    validator(usernameState.value)

                }

            )


            //Password field
            val passwordState = remember {
                mutableStateOf("")
            }

            LoginFormPasswordField(
                fieldValue = passwordState.value,
                isError = !validator(passwordState.value),
                modifier = textFieldModifier,
                colors = textFieldColors,
                onChange = {
                    passwordState.value = it
                },
                validate = {
                    validator(passwordState.value)
                }

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
                        if (validator(usernameState.value) && validator(passwordState.value)) {
                            onSuccess()
                        }
                    }) {
                    Text(text = "Login")
                }


            }


        }
    }
}
