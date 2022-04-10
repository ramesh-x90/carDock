package com.example.carDock.presentation.dashBoard.fragments.carList.commponents

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource


@Composable
fun CarListHeaderIcon(
    @DrawableRes id : Int ,
    onClicked : () -> Unit
)
{
    IconButton(onClick = onClicked) {
        Icon(painter = painterResource(id = id), contentDescription = null )
    }

}