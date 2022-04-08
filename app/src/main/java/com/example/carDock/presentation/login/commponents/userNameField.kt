package com.example.carDock.presentation.login.commponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoginFormUserNameField(
    fieldValue: String,
    isError: Boolean,
    modifier: Modifier = Modifier.padding(bottom = 10.dp),
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    onChange: (value: String) -> Unit,
    validate: () -> Boolean
) {
    val userNameFieldLabel = remember {
        mutableStateOf("Username")
    }

    OutlinedTextField(
        value = fieldValue,
        modifier = modifier,
        colors = colors,
        label = { Text(text = userNameFieldLabel.value) },
        leadingIcon = {
            Icon(Icons.Filled.AccountBox, contentDescription = "Account")
        },
        onValueChange = {
            onChange(it)
            if (!validate()) {
                userNameFieldLabel.value = "Username is empty"
                return@OutlinedTextField
            }
            userNameFieldLabel.value = "Username"

        },
        maxLines = 1,
        isError = isError
    )
}