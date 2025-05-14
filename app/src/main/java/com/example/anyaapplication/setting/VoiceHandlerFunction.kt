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
        ":"
    )

    val newArray = arrayOf(
        "голова",
        "живот",
        "печень",
        "привет",
        "горло",
        "аллергия",
        "сердце",
        "кашель",
        "кишечник",
        "кровотечение",
        "легкие",
        "перелом",
        "простуда",
        "перелом ребер"
    )

    // audio value start
    val alergiya = MediaPlayer.create(context, R.raw.alergiya)
    val cerdce = MediaPlayer.create(context, R.raw.cerdce)
    val gorlo = MediaPlayer.create(context, R.raw.gorlo)
    val greetind = MediaPlayer.create(context, R.raw.greeting)
    val head = MediaPlayer.create(context, R.raw.head)
    val jivot = MediaPlayer.create(context, R.raw.jivot)
    val kachel = MediaPlayer.create(context, R.raw.kachel)
    val kichechnik = MediaPlayer.create(context, R.raw.kichechnik)
    val krovotechenie = MediaPlayer.create(context, R.raw.krovotechenie)
    val legkie = MediaPlayer.create(context, R.raw.legkie)
    val notunderstand = MediaPlayer.create(context, R.raw.notundestand)
    val pechen = MediaPlayer.create(context, R.raw.pechen)
    val perelom = MediaPlayer.create(context, R.raw.perelom)
    val prostuda = MediaPlayer.create(context, R.raw.prostuda)
    val reber = MediaPlayer.create(context, R.raw.reber)
    val save1 = MediaPlayer.create(context, R.raw.save1)
    val save2 = MediaPlayer.create(context, R.raw.save2)
    // audio value end

    if (voiceValue.contains("Аня") && voiceValue.isNotEmpty()){
        if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[0]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[1]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[2]) ||
            voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[3]) ){
            if (voiceValue.lowercase(Locale.ROOT).contains(arrayFromSave[4])){
                save1.start()
            } else {
                save2.start()
            }
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[0])){
            head.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[1])) {
            jivot.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[2])){
            pechen.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[3])) {
            greetind.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[4])){
            gorlo.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[5])){
            alergiya.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[6])){
            cerdce.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[7])){
            kachel.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[8])){
            kichechnik.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[9])){
            krovotechenie.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[10])){
            legkie.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[11])){
            perelom.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[12])){
            prostuda.start()
        } else if (voiceValue.lowercase(Locale.ROOT).contains(newArray[13])){
            reber.start()
        } else {
            notunderstand.start()
        }
    } else {
        notunderstand.start()
    }
}