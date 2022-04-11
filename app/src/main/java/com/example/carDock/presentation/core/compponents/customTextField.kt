package com.example.carDock.presentation.core.compponents


import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.carDock.R
import com.example.carDock.ui.theme.MyColors


@Composable
fun CustomTextField(
    fieldValue: String,
    label: String,
    isError: Boolean,
    modifier: Modifier = Modifier.fillMaxWidth(),
    textColor: Color = MyColors.primaryText,
    onChange: (value: String) -> Unit,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    isPasswordField: Boolean = false,
    keyBoardType: KeyboardType = KeyboardType.Text,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    val hidePasswordState = remember {
        mutableStateOf(isPasswordField)
    }

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor
    )
    OutlinedTextField(
        value = fieldValue,
        modifier = modifier,
        label = { Text(text = label) },
        colors = colors,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = fontSize
        ),
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    painterResource(leadingIcon),
                    contentDescription = ""
                )
            }
        } else null,
        trailingIcon = if (isPasswordField) {
            {
                IconButton(onClick = {
                    if (isPasswordField) {
                        hidePasswordState.value = !hidePasswordState.value
                    }
                }) {
                    if (isPasswordField) {
                        Icon(
                            painter = painterResource(
                                id = if (hidePasswordState.value) {
                                    R.drawable.ic_baseline_remove_red_eye_24
                                } else {
                                    R.drawable.ic_baseline_close_24
                                }
                            ), contentDescription = "Password"
                        )

                    } else if (trailingIcon != null) {
                        Icon(painter = painterResource(id = trailingIcon), contentDescription = "")
                    }


                }
            }
        } else null,
        onValueChange = {

            if(keyBoardType == KeyboardType.Number)
            {
                if (it.isDigitsOnly()) {
                    onChange(it)
                }
            }else{
                onChange(it)
            }


        },
        visualTransformation = if (hidePasswordState.value)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        maxLines = 1,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        ),

    )

}