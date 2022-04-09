package com.example.carDock.presentation.navigation


import com.example.carDock.R

sealed class Routes(val route: String) {
    object HomeScreen : Routes("/HomeScreen")
    object DashBoard : Routes("/DashBoard")
    object UserRegistration : Routes("/UserRegistration")
}


sealed class DashBoardRoutes(val title: String, val icon: Int, val route: String) {
    object CarListScreen : DashBoardRoutes("Cars", R.drawable.ic_baseline_car_24, "/carList")
    object CarRegScreen : DashBoardRoutes("Add", R.drawable.ic_baseline_add_24, "/carReg")
    object Profile : DashBoardRoutes("Profile", R.drawable.ic_baseline_person_24, "/profile")
    object CarDetailsPage : DashBoardRoutes("Car Details", R.drawable.ic_baseline_car_24,"/car_details_page")
    object CartPage : DashBoardRoutes("Cart Page", R.drawable.ic_baseline_shopping_cart_24,"/cart_page")
}

