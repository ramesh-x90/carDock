package com.example.carDock.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carDock.presentation.dashBoard.DashBoard
import com.example.carDock.presentation.dashBoard.fragments.carList.CarListView
import com.example.carDock.presentation.dashBoard.fragments.carList.fragments.CarDetailsPage
import com.example.carDock.presentation.dashBoard.fragments.carList.fragments.CartPage
import com.example.carDock.presentation.dashBoard.fragments.carReg.CarRegView
import com.example.carDock.presentation.dashBoard.fragments.profile.profileView
import com.example.carDock.presentation.login.LoginScreen
import com.example.carDock.presentation.userRegistration.UserRegistrationView

@Composable
fun Navigation(navController: NavHostController) {
//    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(
            route = Routes.HomeScreen.route
        ) {
            LoginScreen(navController = navController)
        }

//        composable(
//            route = Routes.DashBoard.route + "/{arg1}/{id}",
//            arguments = listOf(navArgument(name = "arg1") {
//                type = NavType.StringType
//            }, navArgument(name = "id") {
//                type = NavType.IntType
//            })
//        ) { entry ->
//            val args = entry.arguments
//            DashBoard(
//                navController = navController,
//                arg = args?.getString("arg1"),
//                id = args?.getInt("id")
//            )
//        }

        composable(
            route = Routes.DashBoard.route
        ) {
            DashBoard(navController = navController)
        }

        composable(
            route = Routes.UserRegistration.route
        ) {
            UserRegistrationView(navController = navController)
        }


    }
}


@Composable
fun BottomNavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = DashBoardRoutes.CarListScreen.route) {
        composable(route = DashBoardRoutes.CarListScreen.route) {
            CarListNavGraph()
        }

        composable(route = DashBoardRoutes.CarRegScreen.route) {
            CarRegView()
        }

        composable(route = DashBoardRoutes.Profile.route) {
            profileView()
        }
    }
}


@Composable
fun CarListNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = InnerCarListRoute.CarListPage.route) {
        composable(route = InnerCarListRoute.CarListPage.route) {
            CarListView(navController = navController)
        }

        composable(
            route = InnerCarListRoute.CarDetailsPage.route + "/{id}",
            arguments = listOf(navArgument(name = "id")
            {
                type = NavType.LongType
            }
            )
        ) {
            CarDetailsPage(navController = navController, it.arguments?.getLong("id"))
        }

        composable(route = InnerCarListRoute.CartPage.route) {
            CartPage(navController = navController)
        }
    }
}


















