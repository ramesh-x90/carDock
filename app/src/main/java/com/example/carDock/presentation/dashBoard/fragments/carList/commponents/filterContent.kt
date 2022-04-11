package com.example.carDock.presentation.dashBoard.fragments.carList.commponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.carDock.domain.util.filterUtil.OrderType
import com.example.carDock.presentation.core.compponents.CustomTextField
import com.example.carDock.presentation.dashBoard.fragments.carList.CarListEvents
import com.example.carDock.presentation.dashBoard.fragments.carList.CarListViewModel
import com.example.carDock.ui.theme.MyColors


@Composable
fun FilterContent(viewModel: CarListViewModel) {


    LazyColumn(modifier = Modifier.fillMaxHeight(0.6f))
    {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {


                val brandList = viewModel.filterState.value.brandList

                if (brandList != null) {
                    Text(text = "Brands")
                    LazyRow(modifier = Modifier.fillMaxWidth())
                    {

                        items(brandList) {

                            val butColors = ButtonDefaults.buttonColors(
                                backgroundColor = if (it == viewModel.filterState.value.filteredBrand) MyColors.primary else MyColors.secondary
                            )

                            Button(
                                onClick = {
                                    if (it != viewModel.filterState.value.filteredBrand)
                                        viewModel.onEvent(CarListEvents.OnBrandChange(it))
                                    else viewModel.onEvent(CarListEvents.OnBrandReset)
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
                                backgroundColor = if (it == viewModel.filterState.value.filteredModel) MyColors.primary else MyColors.secondary
                            )

                            Button(onClick = {

                                if (it != viewModel.filterState.value.filteredModel)
                                    viewModel.onEvent(CarListEvents.OnModelChange(it))
                                else viewModel.onEvent(CarListEvents.OnModelRest)

                            }, modifier = Modifier.padding(all = 1.dp), colors = butColors) {
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
                            backgroundColor = if (it.name == viewModel.filterState.value.filteredFuelType) MyColors.primary else MyColors.secondary
                        )

                        Button(
                            onClick = {

                                if (it.name != viewModel.filterState.value.filteredFuelType)
                                    viewModel.onEvent(CarListEvents.OnFuelTypeChange(it.name))
                                else viewModel.onEvent(CarListEvents.OnFuelTypeReset)

                            },
                            modifier = Modifier.padding(all = 1.dp),
                            colors = butColors

                        ) {
                            Text(text = it.name)
                        }
                    }
                }

                Divider()


                Row(modifier = Modifier.padding(all = 10.dp)) {
                    Checkbox(checked = viewModel.filterState.value.NoSold, onCheckedChange = {
                        viewModel.onEvent(CarListEvents.OnSoldFilter)
                    })
                    Text(text = "Hide Sold Cars", modifier = Modifier.padding(start = 10.dp))

                }

                Row {
                    val fieldModifier = Modifier
                        .weight(1f)
                        .padding(all = 10.dp)
                    CustomTextField(
                        fieldValue = viewModel.filterState.value.lowerPrice.toString(),
                        label = "Min Price",
                        isError = false,
                        onChange = {
                            viewModel.onEvent(CarListEvents.OnLowerBoundPriceChange(it))
                        },
                        modifier = fieldModifier,
                        keyBoardType = KeyboardType.Number
                    )
                    CustomTextField(
                        fieldValue = viewModel.filterState.value.upperPrice.toString(),
                        label = "Max Price",
                        isError = false,
                        onChange = {
                            viewModel.onEvent(CarListEvents.OnUpperBoundPriceChange(it))

                        },
                        modifier = fieldModifier,
                        keyBoardType = KeyboardType.Number
                    )
                }

                Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically) {

                    val btnModifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 5.dp)

                    Box(
                        modifier = btnModifier.clickable {
                            viewModel.onEvent(CarListEvents.OnSortByDate)

                        },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "Date")

                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = btnModifier.clickable {
                            viewModel.onEvent(CarListEvents.OnSortByPrice)

                        },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Price")
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {
                        viewModel.onEvent(CarListEvents.OnOrderChange)

                    }) {
                        Icon(
                            imageVector =
                            if (viewModel.filterState.value.orderType == OrderType.Ascending) {
                                Icons.Filled.KeyboardArrowDown
                            } else {
                                Icons.Filled.KeyboardArrowUp
                            }, contentDescription = ""
                        )

                    }


                    Spacer(modifier = Modifier.weight(1f))



                    Button(onClick = {
                        viewModel.onEvent(CarListEvents.OnFilterClear)
                    }, modifier = btnModifier) {
                        Text(text = "Clear Filter")
                    }
                }



            }
        }



    }









}