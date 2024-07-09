package com.example.todolist_compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomDrawer(title:String, action: @Composable ()->Unit = {}){
    var isOpen by remember {
        mutableStateOf(false)
    }
    val modifier = Modifier.clickable { isOpen = !isOpen}
    Row(

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 60.dp)
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp)
        )
        Icon(
            imageVector = if(isOpen) Icons.Filled.KeyboardArrowUp
                else  Icons.Filled.KeyboardArrowDown,
            contentDescription = "Open",
            modifier = Modifier.padding(end = 12.dp)
        )
    }
    if (isOpen){
        action()
    }
    HorizontalDivider()
}