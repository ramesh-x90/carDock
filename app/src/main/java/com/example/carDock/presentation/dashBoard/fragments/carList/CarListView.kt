package com.example.carDock.presentation.dashBoard.fragments.carList


import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.carDock.R
import com.example.carDock.presentation.dashBoard.commponents.core.PageHeader
import com.example.carDock.presentation.dashBoard.fragments.carList.commponents.CarItem
import com.example.carDock.presentation.dashBoard.fragments.carList.commponents.CarListHeaderIcon
import com.example.carDock.presentation.dashBoard.fragments.carList.commponents.FilterContent
import com.example.carDock.presentation.navigation.DashBoardRoutes
import com.example.carDock.ui.theme.MyColors


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarListView(navController: NavHostController) {

    val viewModel = AppModule.getViewModelServiceLocator().getCarListScreenViewModel()



    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        stickyHeader {
            PageHeader(
                header = "Car List",
                leading = {
                    CarListHeaderIcon(
                        id = R.drawable.ic_baseline_filter_list_24,
                        onClicked =
                        {
                            viewModel.onEvent(CarListEvents.Toggled)
                        },
                    )
                },
                trailing = {
                    CarListHeaderIcon(
                        id = R.drawable.ic_baseline_shopping_cart_24,
                        onClicked = {},
                    )

                },
                content = { if (viewModel.state.value.Toggled) FilterContent(viewModel) }

            )

        }

        if (viewModel.state.value.carList.isEmpty()) {

            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                {
                    Text(text = "List is Empty", color = MyColors.primaryLight)
                }
            }


        } else {

            items(viewModel.state.value.carList) {
                CarItem(it) { car ->
                    navController.navigate(route = DashBoardRoutes.CarDetailsPage.route + "/${car.id}")
                }
            }

        }





        item {
            Spacer(modifier = Modifier.size(100.dp))
        }

    }


}

