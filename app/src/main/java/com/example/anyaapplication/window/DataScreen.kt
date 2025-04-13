package com.example.anyaapplication.window

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.anyaapplication.setting.DataStoreManager
import com.example.anyaapplication.setting.SettingData
import com.example.anyaapplication.setting.room.ListItem
import com.example.anyaapplication.setting.room.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun DataScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
    dataStoreManager: DataStoreManager,
    userName: MutableState<String>,
    userAge: MutableState<String>,
    userHeight: MutableState<String>,
    userWeight: MutableState<String>,
    userBloodType: MutableState<String>,
    userMedicines: MutableState<String>,
    navController: NavController
){

    val itemList = mainViewModel.itemList.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var enabledFromExpand by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 64.dp)
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
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Укажите Ваши параметры здесь:",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
            IconButton(
                onClick = {
                    enabledFromExpand = !enabledFromExpand
                }
            ) {
                if (enabledFromExpand){
                    Icon(
                        Icons.Rounded.KeyboardArrowUp,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        }
        if (enabledFromExpand)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(8.dp)
            ) {
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    TextField(
                        value = userName.value,
                        onValueChange = { userName.value = it },
                        label = { Text("Enter your name") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 0.5.sp,
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start
                        )
                    )
                    TextField(
                        value = userAge.value,
                        onValueChange = { userAge.value = it },
                        label = { Text("Enter your age") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 0.5.sp,
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start
                        )
                    )
                    TextField(
                        value = userHeight.value,
                        onValueChange = { userHeight.value = it },
                        label = { Text("Enter your height") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 0.5.sp,
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start
                        )
                    )
                    TextField(
                        value = userWeight.value,
                        onValueChange = { userWeight.value = it },
                        label = { Text("Enter your weight") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 0.5.sp,
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start
                        )
                    )
                    TextField(
                        value = userBloodType.value,
                        onValueChange = { userBloodType.value = it },
                        label = { Text("Enter your bloodType") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 0.5.sp,
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start
                        )
                    )
                    TextField(
                        value = userMedicines.value,
                        onValueChange = { userMedicines.value = it },
                        label = { Text("Enter your medicine") },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 0.5.sp,
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start
                        )
                    )
                    Button(
                        onClick = {
                            scope.launch {
                                dataStoreManager.saveSettings(
                                    SettingData(
                                        userAge = userAge.value,
                                        userName = userName.value,
                                        userMedicines = userMedicines.value,
                                        userWeight = userWeight.value,
                                        userBloodType = userBloodType.value,
                                        userHeight = userHeight.value
                                    )
                                )
                            }
                        }
                    ) {
                        Text(text = "save setting")
                    }
                }
            }
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            flingBehavior = ScrollableDefaults.flingBehavior(),
            state = listState,
        ) {
            items(itemList.value) { item ->
                ListItem(item,
                    {
                        mainViewModel.nameEntity = it
                        mainViewModel.nameText.value = it.name
                        mainViewModel.hourInt.intValue = it.hour
                        mainViewModel.minuteInt.intValue = it.minute
                        mainViewModel.categoryBoolean.value = it.category
                    },
                    {
                        mainViewModel.deleteItem(it)
                    }
                )
            }
        }
    }
}