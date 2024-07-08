package com.example.todolist_compose.ui.components

import androidx.compose.foundation.background
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
import com.example.todolist_compose.ui.theme.Black
import com.example.todolist_compose.ui.theme.DarkGray
import com.example.todolist_compose.ui.theme.Gray
import com.example.todolist_compose.ui.theme.IceWhite
import com.example.todolist_compose.ui.theme.Secondary

@Composable
@Preview
fun TripleSwitch(modifier: Modifier = Modifier){
    val selectedIndex = 0
    val categories = listOf("Pendentes","Em progresso","Terminados")
    Card(
        modifier = modifier.clip(RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            categories.forEachIndexed{ index, description ->
                Text(
                    text = description,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (index == selectedIndex) Black else DarkGray,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (index == selectedIndex) Color.White
                            else Color.Transparent
                        )
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}