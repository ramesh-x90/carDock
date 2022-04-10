package com.example.carDock.presentation.dashBoard.fragments.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.use_case.UserUseCases
import com.example.carDock.domain.util.utilModels.UserFlowData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    val state = mutableStateOf(
        UserFlowData()
    )

    private var job : Job? = null

    init {
        getUserData()
    }

    private fun getUserData()
    {
        job?.cancel()
        job = viewModelScope.launch {
            UserUseCases.getUserDataFlow()?.collect {
                state.value = it
            }
        }

    }
}