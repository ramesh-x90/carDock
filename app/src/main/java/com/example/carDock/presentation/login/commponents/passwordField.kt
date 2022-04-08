package com.example.carDock.presentation.login.commponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.carDock.R


@Composable
fun LoginFormPasswordField(
    fieldValue: String,
    isError: Boolean,
    modifier: Modifier = Modifier.padding(bottom = 10.dp),
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    onChange: (value: String) -> Unit,
    validate: () -> Boolean
) {

    val passwordFieldLabel = remember {
        mutableStateOf("Password")
    }
    val hidePasswordState = remember {
        mutableStateOf(true)
    }
    OutlinedTextField(
        value = fieldValue,
        modifier = modifier,
        label = { Text(text = passwordFieldLabel.value) },
        colors = colors,
        leadingIcon = {
            Icon(
                painterResource(R.drawable.ic_baseline_privacy_tip_24),
                contentDescription = "Password"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                hidePasswordState.value = !hidePasswordState.value
            }) {
                Icon(
                    painter = painterResource(
                        id = if (hidePasswordState.value) {
                            R.drawable.ic_baseline_remove_red_eye_24
                        } else {
                            R.drawable.ic_baseline_close_24
                        }
                    ), contentDescription = "Password"
                )
            }
        },
        onValueChange = {
            onChange(it)
            if (!validate()) {
                passwordFieldLabel.value = "Password is empty"
                return@OutlinedTextField
            }
            passwordFieldLabel.value = "Password"
        },
        visualTransformation = if (hidePasswordState.value)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        maxLines = 1,
        isError = isError
    )

}