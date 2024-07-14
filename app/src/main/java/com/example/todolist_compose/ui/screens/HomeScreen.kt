package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
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
import com.example.todolist_compose.model.TaskStatus
import com.example.todolist_compose.state.HomeState
import com.example.todolist_compose.ui.components.AppBottomBar
import com.example.todolist_compose.ui.components.HomeTaskCard
import com.example.todolist_compose.ui.components.HomeTopBar
import com.example.todolist_compose.ui.components.TripleSwitch
import com.example.todolist_compose.ui.components.toBrazilianDateFormat
import com.example.todolist_compose.ui.theme.AppTheme

@Composable
fun HomeScreen(
    state:HomeState,
    navController:NavHostController,
    onEvent:(HomeEvents)->Unit,
    modifier: Modifier = Modifier
){
    AppTheme{
        Scaffold(
            topBar = {
                HomeTopBar(navController, modifier.padding(horizontal = 12.dp))
            },
            bottomBar = {
                AppBottomBar(text = "Create task"
                ) { navController.navigate("newTask") }
            }
        ) { paddingValues ->
            Column(
                modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
            ) {
                HomeBody(state = state, navController = navController, onEvent = onEvent)
            }
        }
    }
}

@Composable
fun HomeBody(
    state: HomeState,
    navController: NavHostController,
    onEvent:(HomeEvents)->Unit
){
    TripleSwitch(state, onEvent)
    if (state.tasks.isEmpty()){
        EmptyListMessage(text = when(state.selectedTaskState){
            TaskStatus.TO_DO -> "É aqui que você encontrará suas tarefas não iniciadas."
            TaskStatus.DOING -> "É aqui que você encontrará suas tarefas em andamento."
            TaskStatus.DONE -> "É aqui que você encontrará suas tarefas finalizadas."
        })
    }
    else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(start = 4.dp)
                ) {
                    Text(
                        text = "Lista",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = when(state.selectedTaskState){
                            TaskStatus.TO_DO -> "Itens ainda não iniciados"
                            TaskStatus.DOING -> "Itens marcados como em andamento"
                            TaskStatus.DONE -> "Itens marcados como concluídos"
                        },
                    )
                }
            }
            items(items = state.tasks, key = {item: Task -> item.id!! }){ item ->
                HomeTaskCard(task = item, navController = navController) {
                    onEvent(HomeEvents.DeleteTask(item))
                }
            }
        }
    }

}

@Composable
fun EmptyListMessage(text:String, modifier:Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            colors = CardDefaults.cardColors(),
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
        }
        Text(
            text = "Nada aqui. Por agora.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}