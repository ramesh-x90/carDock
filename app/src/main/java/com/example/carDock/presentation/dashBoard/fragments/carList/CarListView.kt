package com.example.carDock.presentation.dashBoard.fragments.carList


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carDock.AppModule
import com.example.carDock.presentation.dashBoard.commponents.core.pageHeader
import com.example.carDock.presentation.dashBoard.fragments.carList.commponents.CarItem
import com.example.carDock.presentation.navigation.InnerCarListRoute
import com.example.carDock.ui.theme.MyColors


@Composable
fun CarListView(navController: NavHostController) {

    val viewModel = AppModule.getViewModelServiceLocator().getCarListScreenViewModel()

    Column {
        pageHeader("Car List")
        if (viewModel.state.value.carList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                Text(text = "List is Empty", color = MyColors.primaryLight)

            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {

                items(viewModel.state.value.carList) {
                    CarItem(it) { car ->
                        navController.navigate(route = InnerCarListRoute.CarDetailsPage.route + "/${car.id}")
                    }
                }



                item {
                    Spacer(modifier = Modifier.size(100.dp))
                }

            }

        }


    }


}

