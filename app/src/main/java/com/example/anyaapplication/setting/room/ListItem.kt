package com.example.anyaapplication.setting.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(item: NameEntity,
             onClick: (NameEntity) -> Unit,
             onClickDelete: (NameEntity) -> Unit,
             valueFromClose: MutableState<Boolean>){

    var arrowIconButton by remember { mutableStateOf(false) }

    Row (verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, end = 4.dp)
    ) {
        Card(modifier = Modifier
            .padding(end = 4.dp)
            .clickable {
                valueFromClose.value = !valueFromClose.value
                onClick(item)
            })  {
            Row (
                modifier = Modifier.padding(2.dp)
            ){
                Text(item.hour + ":" + item.minute, fontSize = 32.sp)
            }
        }
        Card (modifier = Modifier
            .padding(start = 4.dp)
            .fillMaxWidth(0.85f)
            .clickable {
                valueFromClose.value = !valueFromClose.value
                onClick(item)
            }) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            ) {
                Text(modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(2.dp),
                    text = item.name.trimIndent(),
                    maxLines = if (arrowIconButton) { 100 } else { 1 },
                    fontSize = 18.sp
                )
                if (item.name.length > 20){
                    IconButton(onClick = {arrowIconButton = !arrowIconButton}) {
                        if (arrowIconButton){
                            Icon(
                                Icons.Rounded.KeyboardArrowUp,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
        IconButton(onClick = { onClickDelete (item) }) {
            Icon(Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}