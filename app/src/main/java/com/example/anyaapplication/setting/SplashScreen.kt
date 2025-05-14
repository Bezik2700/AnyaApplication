package com.example.anyaapplication.setting

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.anyaapplication.R
import com.example.anyaapplication.window.Navigate
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = { OvershootInterpolator(4f).getInterpolation(it) }))
        delay(1000L)
        navController.navigate(Navigate.MainScreen.route)
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painterResource(R.drawable.appicon),
                contentDescription = null,
                modifier = Modifier.scale(scale.value)
            )
            Text("povered by BEZIKS")
        }
    }
}