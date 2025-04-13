package com.example.anyaapplication.window

import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.anyaapplication.setting.room.MainViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import com.example.anyaapplication.R
import com.example.anyaapplication.setting.voiceHandlerFunction

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    getSpeechInput: () -> Unit,
    outputTxt: String,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
    outputTxtDelete: () -> Unit,
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    val mContext = LocalContext.current

    // audio files start
    val audioBelly = MediaPlayer.create(mContext, R.raw.belly)
    val audioHead = MediaPlayer.create(mContext, R.raw.head)
    val audioLeg = MediaPlayer.create(mContext, R.raw.leg)
    val audioGreeting = MediaPlayer.create(mContext, R.raw.greeting)
    val audioReminder1 = MediaPlayer.create(mContext, R.raw.reminder1)
    val audioReminder2 = MediaPlayer.create(mContext, R.raw.reminder2)
    val audioUnderstand = MediaPlayer.create(mContext, R.raw.understand)
    val audioOpening = MediaPlayer.create(mContext, R.raw.opening)
    // audio files end

    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        //is24Hour = true,
    )

    LaunchedEffect(key1 = true) {
        scope.launch {
            if (outputTxt.isNotEmpty()) {
                outputTxtDelete()
            }
        }
    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            if (voiceHandlerFunction(voiceValue = outputTxt) == "напоминание создано") {
                mainViewModel.nameText.value = outputTxt
                mainViewModel.hourInt.intValue =
                    outputTxt.substringAfterLast("в ").substringBefore(":").toInt()
                mainViewModel.minuteInt.intValue =
                    outputTxt.substringAfter(":").substringBefore(" ").toInt()
                mainViewModel.categoryBoolean.value = true
                mainViewModel.insertItem()
            } else if (voiceHandlerFunction(voiceValue = outputTxt) == "напоминание создано, укажите время во вкладке заметки") {
                mainViewModel.nameText.value = outputTxt
                mainViewModel.hourInt.intValue = timePickerState.hour
                mainViewModel.minuteInt.intValue = timePickerState.minute
                mainViewModel.categoryBoolean.value = true
                mainViewModel.insertItem()
            }
        }
    }

    if (voiceHandlerFunction(voiceValue = outputTxt) == "напоминание создано")
        audioReminder2.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "напоминание создано, укажите время во вкладке заметки")
        audioReminder1.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "карту")
        audioOpening.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "живот")
        audioBelly.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "нога")
        audioLeg.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "голова")
        audioHead.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "привет")
        audioGreeting.start()
    else if (voiceHandlerFunction(voiceValue = outputTxt) == "непонимаю") {
        audioUnderstand.start()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .padding(top = 64.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Navigate.DataScreen.route)
                }
            ) {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
        Card(
            modifier = Modifier
                .size(360.dp)
                .clip(CircleShape)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.anyapng),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        IconButton(
            onClick = { getSpeechInput() },
            modifier = Modifier.padding(top = 96.dp)
        ) {
            Icon(
                Icons.Rounded.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
        IconButton(
            onClick = { outputTxtDelete() },
            modifier = Modifier.padding(top = 96.dp)
        ) {
            Icon(
                Icons.Rounded.Refresh,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
        Text(
            text = outputTxt,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = voiceHandlerFunction(voiceValue = outputTxt),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}