package com.example.todolist_compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 60.dp)
    ) {
        val modifier = Modifier.clickable { isOpen = !isOpen}
        Text(
            text = title,
            modifier = modifier
        )
        Icon(
            imageVector = if(isOpen) Icons.Filled.KeyboardArrowUp
                else  Icons.Filled.KeyboardArrowDown,
            contentDescription = "Open",
            modifier = modifier
        )
    }
    if (isOpen){
        action()
    }
    HorizontalDivider()
}