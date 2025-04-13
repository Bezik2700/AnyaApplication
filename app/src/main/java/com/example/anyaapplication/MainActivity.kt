package com.example.anyaapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.anyaapplication.setting.DataStoreManager
import com.example.anyaapplication.ui.theme.AnyaApplicationTheme
import com.example.anyaapplication.window.NavController
import java.util.Locale

/*private lateinit var mapView: MapView*/

class MainActivity : ComponentActivity() {

    private var outputTxt by mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // data store manager
        val dataStoreManager = DataStoreManager(this)

        // yandex mapkit activity
        /*MapKitFactory.setApiKey("a2ebd5ba-654c-420a-9fe9-54ef82dc9c05")
        MapKitFactory.initialize(this)
        setContentView(R.layout.mapview)
        mapView = findViewById(R.id.mapview)*/

        setContent {
            AnyaApplicationTheme {
                val userName = remember { mutableStateOf("") }
                val userAge = remember { mutableStateOf("") }
                val userHeight = remember { mutableStateOf("") }
                val userWeight = remember { mutableStateOf("") }
                val userBloodType = remember { mutableStateOf("") }
                val userMedicines = remember { mutableStateOf("") }

                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSettings().collect { settings ->
                        userName.value = settings.userName
                        userAge.value = settings.userAge
                        userHeight.value = settings.userHeight
                        userWeight.value = settings.userWeight
                        userBloodType.value = settings.userBloodType
                        userMedicines.value = settings.userMedicines
                    }
                }
                NavController(
                    navController = rememberNavController(),
                    outputTxt = outputTxt,
                    getSpeechInput = { getSpeechInput(this) },
                    outputTxtDelete = { outputTxtDelete() },
                    dataStoreManager = dataStoreManager,
                    userName = userName,
                    userAge = userAge,
                    userHeight = userHeight,
                    userMedicines = userMedicines,
                    userWeight = userWeight,
                    userBloodType = userBloodType
                )
            }
        }
    }
    private fun getSpeechInput(context: Context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            makeText(context, "Speech not Available", LENGTH_SHORT).show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.EXTRA_RESULTS
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
            startActivityForResult(intent, 101)
        }
    }

    @Deprecated("")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            outputTxt = result?.get(0).toString()
        }
    }

    private fun outputTxtDelete(){
        outputTxt = ""
    }

    /*override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }*/
}