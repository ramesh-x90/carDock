package com.example.carDock.presentation.dashBoard.fragments.carDetailsPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carDock.AppModule
import com.example.carDock.R
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
                        painter = painterResource(id = R.mipmap.ic_launcher),
                        contentDescription = null ,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth

                    )
                    Text(text = viewModel.state.value.brand, color = MyColors.primaryText)
                    Text(text = viewModel.state.value.model, color = MyColors.primaryText)
                    Text(text = viewModel.state.value.chassis_no, color = MyColors.primaryText)
                    Text(text = viewModel.state.value.engine_no, color = MyColors.primaryText)
                    Text(text = viewModel.state.value.fuelType, color = MyColors.primaryText)
                    Text(text = "${viewModel.state.value.seller}", color = MyColors.primaryText)
                    Text(text = "${viewModel.state.value.timestamp}", color = MyColors.primaryText)
                    Text(text = "${viewModel.state.value.Color}", color = MyColors.primaryText)
                    Text(text = "${viewModel.state.value.price}", color = MyColors.primaryText)

                    Button(
                        onClick = { /*TODO*/ } ,
                        modifier = Modifier.fillMaxWidth() ,
                        colors = btnColors) {
                        Text(text = "Buy")
                    }


                }
            }



        }







    }

}


