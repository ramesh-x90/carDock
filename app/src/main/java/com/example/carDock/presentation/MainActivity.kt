package com.example.carDock.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.carDock.presentation.navigation.Navigation
import com.example.carDock.ui.theme.CarDockTheme
import com.example.carDock.ui.theme.MyColors


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.example.carDock.AppModule.setContext(this)

        setContent {
            val navController = rememberNavController()
            androidx.compose.material.Surface(
                color = MyColors.primaryLight,
                modifier = Modifier.fillMaxSize()
            ) {

                CarDockTheme(darkTheme = true) {
                    Navigation(navController)

                }
            }
        }
    }

}







