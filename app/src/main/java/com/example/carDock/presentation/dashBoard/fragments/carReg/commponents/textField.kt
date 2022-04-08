package com.example.carDock.presentation.dashBoard.fragments.carReg.commponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import com.example.carDock.ui.theme.MyColors


@Composable
fun RegFormTextFiled(
    value: String,
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    error: Boolean = false,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onValueChange: (it: String) -> Unit = {},
    onFocusChanged: (it: Boolean) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        modifier = modifier
            .onFocusChanged {
                onFocusChanged(it.isFocused)
            },
        isError = error,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MyColors.primaryText
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        )
    )
}


//RegFormTextFiled(
//value = textState.value,
//label = label,
//error = false,
//onValueChange =
//{
//
//},
//onFocusChanged =
//{
//
//}
//
//)