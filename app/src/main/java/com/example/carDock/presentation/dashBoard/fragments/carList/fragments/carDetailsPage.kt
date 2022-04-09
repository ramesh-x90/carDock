package com.example.carDock.presentation.dashBoard.fragments.carList.fragments

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.use_case.CarUseCases
import com.example.carDock.ui.theme.MyColors

@Composable
fun CarDetailsPage(navController: NavHostController, id: Long?) {
    val car: Car? = null

    LazyColumn(modifier = Modifier.fillMaxSize())
    {
        item {
            Text(text = "${car?.id}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.brand}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.model}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.chassis_no}", color = MyColors.primaryText)
        }
        item {
            Text(text = "${car?.engine_no}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.fuelType}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.seller}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.timestamp}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.Color}", color = MyColors.primaryText)
        }

        item {
            Text(text = "${car?.price}", color = MyColors.primaryText)
        }
    }

}


