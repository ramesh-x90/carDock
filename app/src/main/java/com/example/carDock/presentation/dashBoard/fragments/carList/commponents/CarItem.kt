package com.example.carDock.presentation.dashBoard.fragments.carList.commponents


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.carDock.R
import com.example.carDock.domain.model.Car
import com.example.carDock.ui.theme.MyColors


@Composable
fun CarItem(car: Car, onClick: (car: Car) -> Unit) {
    val pad = Modifier.padding(all = 10.dp)
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .height(80.dp)
            .clickable {
                onClick(car)
            },
        backgroundColor = MyColors.primary

    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxSize()
        ) {
//            Image(
//                //don't have data base no brand and models images hope to fetch them from internet for now
//                //this is done
//                painter = painterResource(R.drawable.ic_baseline_car_24),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .clip(
//                        RoundedCornerShape(100.dp)
//                    ),
//                contentScale = ContentScale.FillHeight
//            )
            Box(
                modifier = Modifier
                .fillMaxHeight().width(50.dp).background(MyColors.secondary),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_car_24),
                    contentDescription = null ,
                    modifier = Modifier
                        .fillMaxSize(),
                    tint = Color(car.Color),

                    )
            }

            Column(modifier = Modifier
                .padding(all = 10.dp)
                .weight(1f)) {
                Text(text = car.brand, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Text(text = car.model, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Spacer(modifier = Modifier.weight(1f))
//                LinearProgressIndicator(
//                    progress = 0.7f,
//                    color = Color(car.Color),
//                    modifier = Modifier.width(50.dp)
//                )
            }

            Column(
                horizontalAlignment = Alignment.End, modifier = Modifier
                    .fillMaxHeight()
                    .padding(all = 10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = car.price.toString() + "/=")

                Spacer(modifier = Modifier.weight(1f))

                IconButton(

                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(30.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(MyColors.secondary)

                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = Color.White
                    )

                }


            }


        }
    }
}