package com.example.carDock.presentation.dashBoard.fragments.carDetailsPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carDock.AppModule
import com.example.carDock.R
import com.example.carDock.presentation.core.compponents.MyToast
import com.example.carDock.presentation.core.compponents.SimpleTextBox
import com.example.carDock.ui.theme.MyColors

@Composable
fun CarDetailsPage(navController: NavHostController, id: Long?) {

    val viewModel = AppModule.getViewModelServiceLocator().getCarDetailsViewModel()

    val btnColors = ButtonDefaults.buttonColors(
        backgroundColor = MyColors.secondary,
        contentColor = MyColors.primaryText
    )

    if (id != null) {
        viewModel.getCar(id)
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(all = 20.dp))
    {
        item {
            
            Card(modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(20.dp)),
                elevation = 20.dp
            ) {
                Column(
                    modifier = Modifier.padding(all = 20.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_car_24),
                        contentDescription = null ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillWidth,
                        colorFilter = ColorFilter.tint(Color(viewModel.state.value.Color))

                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        val modifier = Modifier.weight(1f)

                        SimpleTextBox(
                            value = viewModel.state.value.brand,
                            label = "Brand",
                            modifier = modifier
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        SimpleTextBox(
                            value = viewModel.state.value.model,
                            label = "Brand",
                            modifier = modifier
                        )

                    }

                    SimpleTextBox(
                        value = viewModel.state.value.chassis_no,
                        label = "Chassis Number",
                    )


                    SimpleTextBox(
                        value = viewModel.state.value.engine_no,
                        label = "Engine No",
                    )

                    SimpleTextBox(
                        value = viewModel.state.value.fuelType,
                        label = "Fuel Type",
                    )


                    SimpleTextBox(
                        value = viewModel.state.value.seller,
                        label = "Seller",
                    )

                    SimpleTextBox(
                        value = viewModel.state.value.price.toString(),
                        label = "Price (USD)",
                    )


                    Button(
                        enabled = viewModel.state.value.availability,
                        onClick = {
                                  viewModel.onEvent(
                                      CarPurchaseEvents.OnBuy(
                                          onFailed = {
                                              MyToast(it).show()
                                          },
                                          onSuccess = {
                                              MyToast(it).show()
                                          },


                                          )
                                  )

                        } ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp) ,
                        colors = btnColors) {
                        Text(text = if(viewModel.state.value.availability)"Buy" else "Sold")
                    }


                }
                
            }
            



        }

        
        item { 
            Spacer(modifier = Modifier.height(100.dp))
        }





    }

}


