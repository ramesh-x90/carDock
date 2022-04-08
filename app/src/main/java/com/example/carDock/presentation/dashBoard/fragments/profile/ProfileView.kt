package com.example.carDock.presentation.dashBoard.fragments.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carDock.presentation.dashBoard.commponents.core.pageHeader

@Composable
fun profileView() {
    Column(modifier = Modifier.fillMaxWidth()) {
        pageHeader("Profile")
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(100) {
                Text(text = "$it")
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }
}