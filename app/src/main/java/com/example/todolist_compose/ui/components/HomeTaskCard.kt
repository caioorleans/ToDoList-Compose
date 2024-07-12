package com.example.todolist_compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todolist_compose.events.HomeEvents
import com.example.todolist_compose.model.Task

@Composable
fun HomeTaskCard(
    task:Task,
    navController:NavHostController,
    onEvent:()->Unit
){
    var isOpen by remember {
        mutableStateOf(false)
    }
    val fontSize = 14.sp
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().clickable {
                isOpen = !isOpen
            }
        ) {
            Text(
                text = task.title,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                modifier = Modifier.padding(12.dp)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Open",
                modifier = Modifier.padding(12.dp)
            )
        }
        if (isOpen){
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Descrição",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, end = 28.dp)
                )
                Text(
                    text = task.description,
                    fontSize = fontSize,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(end = 12.dp, bottom = 12.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Expira em",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
                )
                Text(
                    text = task.expirationDate.toBrazilianDateFormat(),
                    fontSize = fontSize,
                    modifier = Modifier.padding(end = 12.dp, bottom = 12.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp, bottom = 6.dp)
            ) {
                InputChip(
                    selected = true,
                    onClick = {navController.navigate("editTask/${task.id}")},
                    label = {
                        Text(text = "Modificar")
                    },
                    modifier = Modifier.padding(end = 12.dp)
                )
                InputChip(
                    selected = true,
                    onClick = {
                        onEvent()
                    },
                    label = {
                        Text(text = "Excluir")
                    },
                    colors = InputChipDefaults.inputChipColors(
                        containerColor = Color.Red,
                        selectedContainerColor = Color.Red
                    )
                )
            }
        }
    }
}