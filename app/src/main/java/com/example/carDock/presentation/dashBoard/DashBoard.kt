package com.example.carDock.presentation.dashBoard


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carDock.presentation.dashBoard.commponents.core.BottomNav
import com.example.carDock.presentation.navigation.BottomNavigationGraph
import com.example.carDock.ui.theme.MyColors


@Composable
fun DashBoard(navController: NavHostController) {
    var text = remember {
        mutableStateOf("welcome to android")
    }

    DashBoardContent()
}


@Composable
fun DashBoardContent() {
    val btnPadding = Modifier.padding(all = 10.dp)
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNav(navController)
        }
    ) {
        Surface(color = MyColors.primary, modifier = Modifier.fillMaxHeight(1f)) {
            BottomNavigationGraph(navController = navController)

        }

    }

}

