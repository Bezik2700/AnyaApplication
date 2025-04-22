package com.example.anyaapplication.setting.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(item: NameEntity,
             onClick: (NameEntity) -> Unit,
             onClickDelete: (NameEntity) -> Unit){

    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp, end = 4.dp)
    ) {
        Card(modifier = Modifier
            .padding(end = 4.dp)
            .clickable { onClick(item) })  {
            Row (
                modifier = Modifier.padding(2.dp)
            ){
                Text(item.hour + ":" + item.minute, fontSize = 36.sp)
            }
        }
        Card (modifier = Modifier
            .padding(start = 4.dp)
            .clickable { onClick(item) }) {
            Text(modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(2.dp),
                text = item.name.trimIndent(),
                fontSize = 36.sp
            )
        }
        IconButton(onClick = { onClickDelete (item) }) {
            Icon(Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}