package com.example.carDock.presentation.login


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carDock.R
import com.example.carDock.presentation.login.commponents.LoginForm
import com.example.carDock.presentation.navigation.Routes
import com.example.carDock.ui.theme.MyColors

@Composable
fun LoginScreen(navController: NavController) {
    Surface(color = MyColors.primaryLight) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_car_24),
                    contentDescription = "car",
                    tint = MyColors.secondaryLight
                )
                Text(text = "Login", fontWeight = FontWeight.Bold, color = MyColors.secondaryLight)

            }

            LoginForm(
                onSuccess = {
                    com.example.carDock.AppModule.getViewModelServiceLocator().resetViewModels()
                    navController.navigate(Routes.DashBoard.route)
                },
                onFailed = {

                }
            )

            //registration button
            val btnModifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(0.3f)
                .shadow(elevation = 10.dp)

            val btnColors = ButtonDefaults.buttonColors(
                backgroundColor = MyColors.primary,
                contentColor = MyColors.primaryText
            )
            Button(
                modifier = btnModifier,
                colors = btnColors,
                onClick = {
                    navController.navigate(route = Routes.UserRegistration.route)

                }) {
                Text(text = "Register")
            }


        }
    }
}


//view level validator
fun validator(value: String): Boolean {
    return value.isNotBlank()

}

