package com.example.anyaapplication.setting

import android.content.Context
import android.media.MediaPlayer
import com.example.anyaapplication.R
import java.util.Locale

fun voiceHandlerFunction (
    voiceValue: String,
    context: Context
) {

    val arrayFromSave = arrayOf(
        "сохрани",
        "напомни",
        "подскажи",
        "запомни",
        ":",
        "карту"
    )

    val newArray = arrayOf(
        "голова",
        "живот",
        "нога",
        "привет"
    )

    // audio value start
    val audioBelly = MediaPlayer.create(context, R.raw.belly)
    val audioHead = MediaPlayer.create(context, R.raw.head)
    val audioLeg = MediaPlayer.create(context, R.raw.leg)
    val audioGreeting = MediaPlayer.create(context, R.raw.greeting)
    val audioReminder1 = MediaPlayer.create(context, R.raw.reminder1)
    val audioReminder2 = MediaPlayer.create(context, R.raw.reminder2)
    val audioUnderstand = MediaPlayer.create(context, R.raw.understand)
    val audioOpening = MediaPlayer.create(context, R.raw.opening)
    // audio value end

    if (voiceValue.contains("Аня") && voiceValue.isNotEmpty()){
        if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[0]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[1]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[2]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[3]) ){
            if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[4])){
                audioReminder2.start()
            } else {
                audioReminder1.start()
            }
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[0])){
            audioHead.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[1])) {
            audioBelly.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[2])){
            audioLeg.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[5])){
            audioOpening.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[3])) {
            audioGreeting.start()
        } else {
            audioUnderstand.start()
        }
    } else {
        audioUnderstand.start()
    }
}