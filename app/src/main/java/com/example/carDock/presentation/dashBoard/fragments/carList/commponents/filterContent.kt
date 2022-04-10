package com.example.carDock.presentation.dashBoard.fragments.carList.commponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carDock.presentation.dashBoard.fragments.carList.CarListEvents
import com.example.carDock.presentation.dashBoard.fragments.carList.CarListViewModel
import com.example.carDock.ui.theme.MyColors


@Composable
fun FilterContent(viewModel: CarListViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        val brandList = viewModel.filterState.value.brandList

        if(brandList != null){
            Text(text = "Brands")
            LazyRow(modifier = Modifier.fillMaxWidth())
            {

                items(brandList) {

                    val butColors = ButtonDefaults.buttonColors(
                        backgroundColor = if(it == viewModel.filterState.value.filteredBrand) MyColors.primary else MyColors.secondary
                    )

                    Button(onClick = {
                        viewModel.onEvent(CarListEvents.OnBrandChange(it))
                    },
                        modifier = Modifier.padding(all = 1.dp),
                        colors = butColors


                    ) {
                        Text(text = it)
                    }
                }
            }
        }


        Divider()


        if (viewModel.filterState.value.modelList.isNotEmpty()) {
            Text(text = "Models")
            LazyRow(modifier = Modifier.fillMaxWidth())
            {

                items(viewModel.filterState.value.modelList) {

                    val butColors = ButtonDefaults.buttonColors(
                        backgroundColor = if(it == viewModel.filterState.value.filteredModel) MyColors.primary else MyColors.secondary
                    )

                    Button(onClick = {
                        viewModel.onEvent(CarListEvents.OnModelChange(it))
                    }, modifier = Modifier.padding(all = 1.dp) , colors = butColors) {
                        Text(text = it)
                    }
                }
            }

        }

        Divider()


        Text(text = "Fuel Types")
        LazyRow(modifier = Modifier.fillMaxWidth())
        {
            items(viewModel.filterState.value.fuelTypes) {

                val butColors = ButtonDefaults.buttonColors(
                    backgroundColor = if(it.name == viewModel.filterState.value.filteredFuelType) MyColors.primary else MyColors.secondary
                )

                Button(onClick = {
                    viewModel.onEvent(CarListEvents.OnFuelTypeChange(it.name))
                },
                    modifier = Modifier.padding(all = 1.dp),
                    colors = butColors

                ) {
                    Text(text = it.name)
                }
            }
        }

        Divider()


        Row {
            Checkbox(checked = viewModel.filterState.value.NoSold, onCheckedChange = {
                viewModel.onEvent(CarListEvents.OnSoldFilter)
            })
            Text(text = "Hide Sold Cars", modifier = Modifier.padding(start = 10.dp))

        }


        Button(onClick = {
            viewModel.onEvent(CarListEvents.OnFilterClear)
        }) {
            Text(text = "Clear Filter")
        }


    }


}