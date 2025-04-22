package com.example.anyaapplication.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.anyaapplication.R

@Composable
fun InformationDialog(
    valueFromCloseInfo: MutableState<Boolean>
){

    Dialog(onDismissRequest = { valueFromCloseInfo.value = false }) {

        Box(modifier = Modifier.fillMaxHeight(0.8f)){
            Image(
                painterResource(R.drawable.testfon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Card (
                modifier = Modifier.fillMaxSize()
            ) {
                Column (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hello, my name is Anya!!!")
                    Image(
                        painterResource(R.drawable.anyapng),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(180.dp)
                    )
                    Text("about Anya text")
                    Text("privacy polucy")
                    Button(
                        onClick = {valueFromCloseInfo.value = false}
                    ) {
                        Text("Apply")
                    }
                }
            }
        }
    }
}
