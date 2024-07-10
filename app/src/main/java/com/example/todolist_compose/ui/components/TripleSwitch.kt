package com.example.todolist_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_compose.events.HomeEvents
import com.example.todolist_compose.model.TaskStatus
import com.example.todolist_compose.state.HomeState

@Composable
fun TripleSwitch(state: HomeState, onEvent:(HomeEvents)->Unit,modifier: Modifier = Modifier){
    val selectedIndex = 0
    Card(
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            for(status in TaskStatus.entries){
                Text(
                    text = status.value,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (status == state.selectedTaskState) Color.Black else Color.DarkGray,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onEvent(HomeEvents.SortTasks(status)) }
                        .background(
                            if (status == state.selectedTaskState) Color.White
                            else Color.Transparent
                        )
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}