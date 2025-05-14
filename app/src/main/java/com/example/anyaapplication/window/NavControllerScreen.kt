package com.example.anyaapplication.window

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.anyaapplication.R
import com.example.anyaapplication.setting.DataStoreManager
import com.example.anyaapplication.setting.SplashScreen

sealed class Navigate(val route: String){
    data object SplashScreen: Navigate("SplashScreen")
    data object MapScreen: Navigate("MapScreen")
    data object MainScreen: Navigate("MainScreen")
    data object DataScreen: Navigate("DataScreen")
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavController(
    navController: NavHostController,
    getSpeechInput: () -> Unit,
    outputTxt: String,
    outputTxtDelete: () -> Unit,
    dataStoreManager: DataStoreManager,
    userName: MutableState<String>,
    userAge: MutableState<String>,
    userHeight: MutableState<String>,
    userWeight: MutableState<String>,
    userBloodType: MutableState<String>,
    userMedicines: MutableState<String>,
    valueFromClose: MutableState<Boolean>,
    valueFromCloseInfo: MutableState<Boolean>
){
    Box(modifier = Modifier){
        Image(
            painterResource(R.drawable.fon1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = Navigate.SplashScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 15000 }) + fadeIn() },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -15000 }) + fadeOut() }
        ) {
            composable (route = Navigate.SplashScreen.route) {
                SplashScreen(
                    navController = navController
                )
            }
            composable(route = Navigate.MainScreen.route) {
                MainScreen(
                    outputTxt = outputTxt,
                    getSpeechInput = getSpeechInput,
                    outputTxtDelete = outputTxtDelete,
                    navController = navController,
                    valueFromCloseInfo = valueFromCloseInfo
                )
            }
            composable(route = Navigate.MapScreen.route) {
                MapScreen(
                    navController = navController
                )
            }
            composable(route = Navigate.DataScreen.route) {
                DataScreen(
                    dataStoreManager = dataStoreManager,
                    userName = userName,
                    userAge = userAge,
                    userHeight = userHeight,
                    userMedicines = userMedicines,
                    userWeight = userWeight,
                    userBloodType = userBloodType,
                    navController = navController,
                    outputTxt = outputTxt,
                    valueFromClose = valueFromClose,
                    valueFromCloseInfo = valueFromCloseInfo
                )
            }
        }
    }
}