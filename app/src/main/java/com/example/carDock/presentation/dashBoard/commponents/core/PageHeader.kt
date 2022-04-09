package com.example.carDock.presentation.dashBoard.commponents.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carDock.ui.theme.MyColors


@Composable
fun PageHeader(header: String, bgColor: Color = MyColors.primaryLight) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = header, modifier = Modifier
                .padding(all = 10.dp),
            fontWeight = FontWeight.W600,
            color = MyColors.primaryText
        )
    }

}