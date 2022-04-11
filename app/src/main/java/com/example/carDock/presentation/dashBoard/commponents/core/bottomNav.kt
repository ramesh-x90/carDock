package com.example.carDock.presentation.dashBoard.commponents.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.carDock.presentation.navigation.DashBoardRoutes
import com.example.carDock.ui.theme.MyColors

@Composable
fun BottomNav(navController: NavHostController) {
    val frags = listOf<DashBoardRoutes>(
        DashBoardRoutes.CarListScreen,
        DashBoardRoutes.CarRegScreen,
        DashBoardRoutes.Profile,
    )
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(bottom = 10.dp)
            .height(50.dp)
            .background(
                Color.Transparent
            )
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))


    ) {
        BottomNavigation(
            backgroundColor = MyColors.primaryLight,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            frags.forEach {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = it.title) },
                    alwaysShowLabel = currentRoute == it.route,
                    selected = currentRoute == it.route,
                    selectedContentColor = MyColors.secondaryLight,
                    unselectedContentColor = MyColors.primaryText,
                    onClick = {
                        if(it.route != currentRoute)
                            navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                        }

                    }
                )
            }
        }
    }
}