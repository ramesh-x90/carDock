package com.example.carDock.presentation.dashBoard.fragments.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carDock.AppModule
import com.example.carDock.presentation.dashBoard.commponents.core.PageHeader
import com.example.carDock.presentation.core.compponents.SimpleTextBox




@Composable
fun ProfileView() {

    val viewModel = AppModule.getViewModelServiceLocator().getProfileViewModel()

    Column() {
        PageHeader("Profile")

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            item {
                Box() {

                    Column(modifier = Modifier.padding(all = 10.dp)) {
                        SimpleTextBox(value = viewModel.state.value.id.toString(), label = "ID",)
                        SimpleTextBox(value = viewModel.state.value.name, label = "Username",)
                        SimpleTextBox(value = viewModel.state.value.email, label = "Email",)
                        SimpleTextBox(value = viewModel.state.value.address, label = "Address",)
                        SimpleTextBox(value = viewModel.state.value.contact_number.toString(), label = "Contact Number",)
                        SimpleTextBox(value = viewModel.state.value.balance.toString(), label = "Balance (USD)",)




                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }


}


