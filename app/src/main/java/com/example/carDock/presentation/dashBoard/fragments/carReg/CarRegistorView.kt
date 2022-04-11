package com.example.carDock.presentation.dashBoard.fragments.carReg


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.carDock.AppModule
import com.example.carDock.domain.model.Car
import com.example.carDock.presentation.core.compponents.MyToast
import com.example.carDock.presentation.dashBoard.commponents.core.PageHeader
import com.example.carDock.presentation.dashBoard.fragments.carReg.commponents.FilteredTextField
import com.example.carDock.presentation.dashBoard.fragments.carReg.commponents.RegFormTextFiled
import com.example.carDock.ui.theme.MyColors


@Composable
fun CarRegView() {

    val viewModel =
        AppModule.getViewModelServiceLocator().getCarRegScreenViewModel()

    Column(modifier = Modifier.fillMaxWidth()) {
        PageHeader("Register a Car")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                FilteredTextField(
                    label = "Brand",
                    value = viewModel.carRegFormState.value.selectedBrand,
                    list = viewModel.carRegFormState.value.brandList ?: emptyList(),
                    onSelected = {
                        viewModel.onEvent(
                            CarRegEvents.OnBrandSelect(it)
                        )
                        it
                    },
                    isError = viewModel.formErrorState.value.brandError
                ) {
                    viewModel.onEvent(
                        CarRegEvents.OnBrandChange(it)
                    )
                }

            }



            item {

                FilteredTextField(
                    label = "Model",
                    value = viewModel.carRegFormState.value.selectedModel,
                    list = viewModel.carRegFormState.value.modelsList ?: emptyList(),
                    onSelected = {
                        viewModel.onEvent(
                            CarRegEvents.OnModelSelect(it)
                        )
                        it
                    },
                    isError = viewModel.formErrorState.value.modelError
                ) {
                    viewModel.onEvent(
                        CarRegEvents.OnModelChange(it)
                    )
                }
            }




            item {

                val label = "Price"

                RegFormTextFiled(
                    value = viewModel.carRegFormState.value.price.let {
                        when {
                            (it < 0) -> ""
                            else -> it.toString()
                        }
                    },
                    label = label,
                    error = viewModel.formErrorState.value.priceError,
                    onValueChange =
                    {

                        viewModel.onEvent(
                            CarRegEvents.OnPriceChange(it)
                        )


                    },
                    keyBoardType = KeyboardType.Number

                )
            }





            item {
                val label = "Chassis no"

                RegFormTextFiled(
                    value = viewModel.carRegFormState.value.chassis_no,
                    label = label,
                    error = viewModel.formErrorState.value.chassisNoError,
                    onValueChange =
                    {
                        viewModel.onEvent(
                            CarRegEvents.OnChassisNoChange(it)
                        )
                    },

                    )
            }






            item {
                val label = "Engine no"

                RegFormTextFiled(
                    value = viewModel.carRegFormState.value.engine_no,
                    label = label,
                    error = viewModel.formErrorState.value.engineNoError,
                    onValueChange =
                    {
                        viewModel.onEvent(
                            CarRegEvents.OnEngineNoChange(it)
                        )
                    },

                    )
            }





            item {
                val dropExpandDrownState = rememberSaveable {
                    mutableStateOf(false)
                }
                Button(
                    onClick = { dropExpandDrownState.value = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MyColors.primaryLight,
                        contentColor = MyColors.primaryText
                    )
                ) {
                    Text(
                        text = viewModel.carRegFormState.value.fuelType.let {
                            when (it.isBlank()) {

                                true -> "Select Fuel type"
                                false -> it

                            }
                        },
//                        color = MyColors.primaryLight

                    )

                }
                DropdownMenu(
                    expanded = dropExpandDrownState.value,
                    onDismissRequest = { dropExpandDrownState.value = false },
                    offset = DpOffset(x = 0.dp, y = 500.dp)
                ) {

                    (viewModel.carRegFormState.value.fuelTypes).forEachIndexed { index, fuelType ->
                        DropdownMenuItem(
                            onClick = {
                                dropExpandDrownState.value = false
                                viewModel.onEvent(CarRegEvents.OnSelectFuelType(fuelType))
                            }
                        ) {
                            Text(
                                text = "${(index + 1)} . ${fuelType.name}",
                                color = MyColors.primaryText
                            )

                        }

                    }

                }

            }



            item {
                Divider()
            }




            item {
                LazyRow(modifier = Modifier.height(60.dp)) {
                    items(Car.colors) {
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .padding(all = 5.dp)
                                .border(
                                    width = 2.dp,
                                    color = MyColors.primaryLight,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .clip(shape = RoundedCornerShape(50.dp))
                                .background(it)
                                .clickable {
                                    viewModel.onEvent(
                                        CarRegEvents.OnSelectColor(it)
                                    )

                                },
                            contentAlignment = Alignment.Center

                        ) {
                            val selectionColor = when (it) {
                                Color.Black -> Color.White
                                else -> MyColors.primaryDark
                            }
                            if (it == viewModel.carRegFormState.value.selectedColor) {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = null,
                                    tint = selectionColor
                                )
                            }

                        }
                    }
                }
            }

            item {
                Divider()
            }


            item {
                Button(onClick =
                {
                    viewModel.onEvent(
                        CarRegEvents.OnRegister(
                            onSuccess = {
                                MyToast(msg = it).show()
                            },
                            onFailed = {
                                MyToast(msg = it).show()
                            }
                        )
                    )

                }, modifier = Modifier.padding(all = 10.dp)
                ) {
                    Text(text = "Submit")
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }
}