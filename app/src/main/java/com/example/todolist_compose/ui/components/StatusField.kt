package com.example.todolist_compose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist_compose.events.UpsertTaskEvents
import com.example.todolist_compose.model.TaskStatus

@Composable
fun StatusField(
    taskStatus: TaskStatus,
    onEvent: (UpsertTaskEvents)->Unit
){
    Text(
        text = "Status",
        modifier = Modifier.padding( top= 8.dp, start=8.dp )
    )
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        for (status in TaskStatus.entries){
            FilterChip(
                selected = taskStatus == status,
                onClick = { onEvent(UpsertTaskEvents.SetStatus(status)) },
                label = { Text(text = status.value) },
                colors = FilterChipDefaults.filterChipColors()
            )
        }
    }
    HorizontalDivider(
    )
}