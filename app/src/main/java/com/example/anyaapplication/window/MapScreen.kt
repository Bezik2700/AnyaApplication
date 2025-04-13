package com.example.anyaapplication.window

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.anyaapplication.R

@SuppressLint("InflateParams")
@Composable
fun MapScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ){
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                IconButton(
                    onClick = {navController.navigate(Navigate.MainScreen.route)}
                ) {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                    )
                }
            }
            AndroidView(
                factory = { context ->
                    val view = LayoutInflater.from(context)
                        .inflate(R.layout.mapview, null, false)
                    view
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.95f)
            )

        }
    }
}