package com.example.anyaapplication.setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anyaapplication.setting.room.MainViewModel

@Composable
fun DialogItem(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
    valueFromClose: MutableState<Boolean>
){

    val context = LocalContext.current

    Dialog(onDismissRequest = { valueFromClose.value = false }) {

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ){
                IconButton(
                    onClick = {
                        valueFromClose.value = false
                    }
                ) {
                    Icon(
                        Icons.Sharp.Close,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp).background(color = Color.White)
                    )
                }
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier.size(width = 120.dp, height = 64.dp)
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = mainViewModel.hourString.value,
                            onValueChange = { mainViewModel.hourString.value = it.take(2) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(0.45f)
                        )
                        Text("|", fontSize = 48.sp)
                        TextField(
                            value = mainViewModel.minuteString.value,
                            onValueChange = { mainViewModel.minuteString.value = it.take(2) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            maxLines = 1,
                        )
                    }
                }
                Card (
                    modifier = Modifier
                        .size(width = 200.dp, height = 64.dp)
                        .padding(start = 4.dp)
                ){
                    TextField(
                        value = mainViewModel.nameText.value,
                        onValueChange = { mainViewModel.nameText.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        maxLines = 1,
                    )
                }
            }
            IconButton(
                onClick = {
                    if (mainViewModel.hourString.value.isNotEmpty() &&
                        mainViewModel.minuteString.value.isNotEmpty() &&
                        mainViewModel.nameText.value.isNotEmpty()
                    ) {
                        mainViewModel.insertItem()
                        valueFromClose.value = false
                    }
                    else {
                        Toast.makeText(context,"Please fill in all the cells",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    Icons.Sharp.Add,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp).background(color = Color.White)
                )
            }
        }
    }
}
