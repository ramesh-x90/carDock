package com.example.carDock.presentation.core.compponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleTextBox(value : String , label : String , modifier: Modifier = Modifier.fillMaxWidth())
{
    OutlinedTextField(
        value = value,
        onValueChange = {} ,
        enabled = false ,
        modifier = modifier,
        label = { Text(text = label) })
}