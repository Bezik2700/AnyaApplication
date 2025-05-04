package com.example.anyaapplication.setting

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                painterResource(R.drawable.fon2),
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
                    Text(stringResource(R.string.about_anya))
                    Image(
                        painterResource(R.drawable.anyapng2),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(180.dp)
                    )
                    Text(stringResource(R.string.anya_description))
                    PrivacyPolicy(R.string.privacy_policy)
                    Button(
                        onClick = {valueFromCloseInfo.value = false}
                    ) {
                        Text(stringResource(R.string.apply))
                    }
                }
            }
        }
    }
}

@Composable
fun PrivacyPolicy(@StringRes text: Int){
    val context = LocalContext.current
    val intent = remember { Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://www.termsfeed.com/live/2c30db3e-8316-40d0-808b-1d24a6288aa3")) }
    TextButton(onClick = { context.startActivity(intent) }) {
        Text(
            stringResource(id = text),
            style = MaterialTheme.typography.labelSmall)
    }
}