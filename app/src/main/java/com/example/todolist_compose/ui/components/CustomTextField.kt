package com.example.todolist_compose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todolist_compose.events.UpsertTaskEvents

@Composable
fun CustomTextField(
    textField:String,
    onEvent:(UpsertTaskEvents) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Título",
            modifier = Modifier.padding(start = 8.dp)
        )
        OutlinedTextField(
            value = textField,
            onValueChange = {onEvent(UpsertTaskEvents.SetTitle(it))},
            textStyle = TextStyle(textAlign = TextAlign.Right),
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
    HorizontalDivider()
}