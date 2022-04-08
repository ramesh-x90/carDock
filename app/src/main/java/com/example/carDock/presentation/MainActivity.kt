package com.example.carDock.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.carDock.presentation.navigation.Navigation
import com.example.carDock.ui.theme.CarDockTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.example.carDock.AppModule.setContext(this)

        setContent {
            val navController = rememberNavController()
            CarDockTheme(darkTheme = true) {
                Navigation(navController)

            }
        }
    }

}







