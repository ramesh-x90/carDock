package com.example.carDock.presentation.userRegistration.commponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.carDock.presentation.core.compponents.CustomTextField


@Composable
fun UserRegPwdField(
    value: String,
    label: String,
    onChange: (text: String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyBoardType: KeyboardType = KeyboardType.Text
) {

    CustomTextField(
        fieldValue = value,
        onChange = onChange,
        isPasswordField = true,
        label = label,
        isError = isError,

        )
}