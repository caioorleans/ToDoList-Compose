package com.example.todolist_compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todolist_compose.TaskDatabase
import com.example.todolist_compose.dao.TaskDao
import com.example.todolist_compose.events.CreateTaskEvents
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import com.example.todolist_compose.state.TaskState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateTaskViewModel(val taskDao:TaskDao, val navController: NavHostController):ViewModel() {
    val state = MutableStateFlow(TaskState())

    fun onEvent(event:CreateTaskEvents){
        when(event){
            CreateTaskEvents.ClearValues -> {
                state.update {it.copy(
                    title = "",
                    description = "",
                    taskStatus = TaskStatus.TO_DO
                )}
            }
            is CreateTaskEvents.SetTitle -> {
                state.update { it.copy(
                    title = event.title
                ) }
            }
            is CreateTaskEvents.SetDescription -> {
                state.update {it.copy(
                    description = event.description
                )}
            }
            is CreateTaskEvents.setStatus -> {
                state.update { it.copy(
                    taskStatus = event.taskStatus
                ) }
            }
            CreateTaskEvents.createTask -> {
                val task = Task(state.value.title, state.value.description, state.value.taskStatus)
                viewModelScope.launch {
                    taskDao.upsertTask(task)
                }
                navController.popBackStack()
            }
            CreateTaskEvents.goToPreviousPage -> {
                navController.popBackStack()
            }
        }
    }
}