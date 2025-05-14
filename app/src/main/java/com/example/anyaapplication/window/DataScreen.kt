package com.example.anyaapplication.window

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.anyaapplication.R
import com.example.anyaapplication.setting.DataStoreManager
import com.example.anyaapplication.setting.DialogItem
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
    valueFromClose: MutableState<Boolean>,
    valueFromCloseInfo: MutableState<Boolean>,
    navController: NavController,
    outputTxt: String
){

    val context = LocalContext.current
    val itemList = mainViewModel.itemList.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var enabledFromExpand by remember { mutableStateOf(false) }

    Box(modifier = Modifier){

        Image(
            painterResource(R.drawable.fon1),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 64.dp)
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
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        enabledFromExpand = !enabledFromExpand
                    }
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.user_info),
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
            }
            if (enabledFromExpand)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
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
                        UserTextField(
                            value = userAge,
                            label = stringResource(R.string.age_label),
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                        UserTextField(
                            value = userName,
                            label = stringResource(R.string.name_label),
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                        UserTextField(
                            value = userWeight,
                            label = stringResource(R.string.weight_label),
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                        UserTextField(
                            value = userHeight,
                            label = stringResource(R.string.height_label),
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                        UserTextField(
                            value = userBloodType,
                            label = stringResource(R.string.blood_type_label),
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                        UserTextField(
                            value = userMedicines,
                            label = stringResource(R.string.medicine_label),
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
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
                                            userHeight = userHeight.value,
                                            valueFromClose = valueFromClose.value,
                                            valueFromCloseInfo = valueFromCloseInfo.value
                                        )
                                    )
                                }
                                scope.launch {
                                    enabledFromExpand = false
                                }
                            }
                        ) {
                            Text(stringResource(R.string.save_settings))
                        }
                    }
                }
            HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), color = Color.Black)
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.user_notifications),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    IconButton(
                        onClick = {
                            if (outputTxt.isNotEmpty()){
                                mainViewModel.insertItem()
                            } else {
                                Toast.makeText(context,
                                    "Please enter your save info on Ann",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Refresh,
                            contentDescription = null
                        )
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), color = Color.Black)
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 4.dp),
                flingBehavior = ScrollableDefaults.flingBehavior(),
                state = listState,
            ) {
                items(itemList.value) { item ->
                    ListItem(
                        item,
                        {
                            mainViewModel.nameEntity = it
                            mainViewModel.nameText.value = it.name
                            mainViewModel.hourString.value = it.hour
                            mainViewModel.minuteString.value = it.minute
                            mainViewModel.categoryBoolean.value = it.category
                        },
                        {
                            mainViewModel.deleteItem(it)
                        },
                        valueFromClose = valueFromClose
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), color = Color.Black)
            Button(
                onClick = {
                    valueFromClose.value = true
                    mainViewModel.nameText.value = ""
                    mainViewModel.hourString.value = ""
                    mainViewModel.minuteString.value = ""
                }
            ){
                Text(stringResource(R.string.add_notification))
            }
            if (valueFromClose.value){
                DialogItem(valueFromClose = valueFromClose)
            }
        }
    }
}

@Composable
fun UserTextField(
    value: MutableState<String>,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction
){
    TextField(
        value = value.value,
        onValueChange = {value.value = it},
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = true,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
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
}