package com.example.carDock.presentation.userRegistration.commponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.carDock.presentation.core.compponents.CustomTextField

@Composable
fun UserRegTextField(
    value: String,
    label: String,
    isError: Boolean = false,
    onChange: (text: String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyBoardType: KeyboardType = KeyboardType.Text,

    ) {

    CustomTextField(
        fieldValue = value,
        onChange = onChange,
        isPasswordField = false,
        label = label,
        isError = isError,
        modifier = modifier,
        keyBoardType = keyBoardType
    )
}