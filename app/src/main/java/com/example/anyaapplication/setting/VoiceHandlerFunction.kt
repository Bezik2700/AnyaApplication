package com.example.anyaapplication.setting

import java.util.Locale

fun voiceHandlerFunction (
    voiceValue: String
): String {

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

    val returnValue: String

    if (voiceValue.contains("Аня")){
        returnValue = if (
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[0]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[1]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[2]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[3]) ){
            if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[4])){
                "напоминание создано"
            } else {
                "напоминание создано, укажите время во вкладке заметки"
            }
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[0])){
            "голова"
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[1])) {
            "живот"
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[2])){
            "нога"
        } else if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[5])){
            "карту"
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[3])) {
            "привет"
        }
        else {
            "непонимаю"
        }
    }
    else {
        returnValue = ""
    }
    return returnValue
}