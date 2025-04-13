package com.example.anyaapplication.setting.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(item: NameEntity,
             onClick: (NameEntity) -> Unit,
             onClickDelete: (NameEntity) -> Unit){

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable {
            onClick(item)
        }){
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row {
                Text(
                    text = item.hour.toString()
                )
                Text(
                    text = ":"
                )
                Text(
                    text = if (item.minute == 0){
                        item.minute.toString() + "0"
                    } else {
                        item.minute.toString()
                    }
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(0.6f).padding(1.dp),
                text = item.name.trimIndent()
            )
            IconButton(onClick = { item.category = !item.category }) {
                Text(item.category.toString())
            }
            IconButton(onClick = { onClickDelete (item) }) {
                Icon(Icons.Filled.Delete, contentDescription = null)
            }
        }
    }
}