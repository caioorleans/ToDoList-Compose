package com.example.todolist_compose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist_compose.events.CreateTaskEvents
import com.example.todolist_compose.state.TaskState
import com.example.todolist_compose.ui.components.AppBottomBar
import com.example.todolist_compose.ui.components.CustomDrawer
import com.example.todolist_compose.ui.components.CustomTextField
import com.example.todolist_compose.ui.components.DatePickerComponent
import com.example.todolist_compose.ui.components.OtherTopAppBar
import com.example.todolist_compose.ui.components.StatusField
import com.example.todolist_compose.ui.theme.Black
import com.example.todolist_compose.ui.theme.ToDOListComposeTheme

@Composable
fun CreateTaskScreen(
    state:TaskState,
    onEvent:(CreateTaskEvents)->Unit
){
    ToDOListComposeTheme {
        Scaffold(
            topBar = {
                OtherTopAppBar(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    goBackAction = { onEvent(CreateTaskEvents.goToPreviousPage) },
                    clearAction = {onEvent(CreateTaskEvents.ClearValues)}
                )
            },
            bottomBar = {
                AppBottomBar(
                    text = "Criar Task",
                    enabled = state.title.isNotBlank() && state.description.isNotBlank()
                ) { onEvent(CreateTaskEvents.createTask) }
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
                        onValueChange = {onEvent(CreateTaskEvents.SetDescription(it))},
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedTextColor = Black,
                            focusedTextColor = Black
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