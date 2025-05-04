package com.example.anyaapplication.window

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.anyaapplication.R
import com.example.anyaapplication.setting.InformationDialog
import com.example.anyaapplication.setting.room.MainViewModel
import com.example.anyaapplication.setting.voiceHandlerFunction
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    getSpeechInput: () -> Unit,
    outputTxt: String,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
    outputTxtDelete: () -> Unit,
    navController: NavController,
    valueFromCloseInfo: MutableState<Boolean>
) {

    val context = LocalContext.current

    val arrayFromSave = arrayOf(
        "сохрани",
        "напомни",
        "подскажи",
        "запомни",
        ":",
        "карту"
    )

    // Timer from save
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        //is24Hour = true,
    )

    // VoiceHandler function doWork
    if (outputTxt.isNotEmpty()){
        voiceHandlerFunction(voiceValue = outputTxt, context = context)
        if (outputTxt.lowercase(Locale.ROOT).contains(arrayFromSave[0]) ||
            outputTxt.lowercase(Locale.ROOT).contains(arrayFromSave[1]) ||
            outputTxt.lowercase(Locale.ROOT).contains(arrayFromSave[2]) ||
            outputTxt.lowercase(Locale.ROOT).contains(arrayFromSave[3]) ){
            if (outputTxt.lowercase(Locale.ROOT).contains(arrayFromSave[4])){
                mainViewModel.nameText.value = outputTxt
                mainViewModel.hourString.value =
                    outputTxt.substringAfterLast("в ").substringBefore(":")
                mainViewModel.minuteString.value =
                    outputTxt.substringAfter(":").substringBefore(" ")
                mainViewModel.categoryBoolean.value = true
                mainViewModel.insertItem()
            } else {
                mainViewModel.nameText.value = outputTxt
                mainViewModel.hourString.value = timePickerState.hour.toString()
                mainViewModel.minuteString.value = timePickerState.minute.toString()
                mainViewModel.categoryBoolean.value = true
                mainViewModel.insertItem()
            }
        }
    }
    outputTxtDelete.invoke()

    // Visual realization
    Box(modifier = Modifier){

        Image(
            painterResource(R.drawable.fon2),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 96.dp,
                        end = 16.dp,
                        start = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        valueFromCloseInfo.value = true
                    }
                ) {
                    Icon(
                        Icons.Rounded.Info,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Navigate.DataScreen.route)
                    }
                ) {
                    Icon(
                        Icons.Rounded.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = Color.Black
                    )
                }
            }
            Image(
                painter = painterResource(R.drawable.anyapng2),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            )
            IconButton(
                onClick = { getSpeechInput() },
                modifier = Modifier.padding(top = 160.dp).size(120.dp).clip(CircleShape)
            ) {
                Image(
                    painterResource(R.drawable.micro),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        if (valueFromCloseInfo.value){
            InformationDialog(valueFromCloseInfo = valueFromCloseInfo)
        }
    }
}