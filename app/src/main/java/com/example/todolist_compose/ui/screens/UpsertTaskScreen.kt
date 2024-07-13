package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist_compose.events.UpsertTaskEvents
import com.example.todolist_compose.ui.components.AppBottomBar
import com.example.todolist_compose.ui.components.CustomDrawer
import com.example.todolist_compose.ui.components.CustomTextField
import com.example.todolist_compose.ui.components.DatePickerComponent
import com.example.todolist_compose.ui.components.OtherTopAppBar
import com.example.todolist_compose.ui.components.StatusField
import com.example.todolist_compose.ui.theme.AppTheme
import com.example.todolist_compose.ui.viewModel.UpsertTaskViewModel

@Composable
fun CreateTaskScreen(
    viewModel:UpsertTaskViewModel,
    navController:NavHostController,
    onEvent:(UpsertTaskEvents)->Unit
){
    val state by viewModel.state.collectAsState()
    AppTheme {
        Scaffold(
            topBar = {
                OtherTopAppBar(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    goBackAction = { navController.popBackStack() },
                    clearAction = {onEvent(UpsertTaskEvents.ClearValues)}
                )
            },
            bottomBar = {
                AppBottomBar(
                    text = if(state.update) "Atualizar task" else "Criar Task",
                    enabled = state.title.isNotBlank()
                            && System.currentTimeMillis() - state.expirationDate < 86400000
                ) {
                    if(state.update) onEvent(UpsertTaskEvents.UpdateTask)
                    else onEvent(UpsertTaskEvents.CreateTask)
                    navController.popBackStack()
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(12.dp)
            ) {
                CustomTextField(state.title, onEvent)
                CustomDrawer(title = "Descrição"){
                    OutlinedTextField(
                        value = state.description,
                        onValueChange = {onEvent(UpsertTaskEvents.SetDescription(it))},
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
                StatusField(state.taskStatus,onEvent)
                DatePickerComponent(state.expirationDate, onEvent)
            }
        }
    }
}