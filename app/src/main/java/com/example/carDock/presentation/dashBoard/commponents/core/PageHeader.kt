package com.example.carDock.presentation.dashBoard.commponents.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carDock.ui.theme.MyColors


@Composable
fun PageHeader(
    header: String,
    bgColor: Color = MyColors.primaryLight,
    leading : @Composable() (() -> Unit) ? = null,
    trailing : @Composable() (() -> Unit) ? = null,
    content : @Composable() (() -> Unit) ? = null,

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {

        Box() {

            Column(
                modifier = Modifier.fillMaxWidth() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Row(
                    horizontalArrangement = Arrangement.Center ,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    leading?.invoke()

                    if(leading != null)
                    {
                        Spacer(modifier = Modifier.weight(1f))

                    }


                    Text(
                        text = header, modifier = Modifier
                            .padding(all = 10.dp),
                        fontWeight = FontWeight.W600,
                        color = MyColors.primaryText
                    )


                    if(trailing != null)
                    {
                        Spacer(modifier = Modifier.weight(1f))

                    }

                    trailing?.invoke()



                }

                content?.invoke()




            }
        }



    }

}