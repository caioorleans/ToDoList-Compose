package com.example.todolist_compose.ui.viewModel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.todolist_compose.database.dao.TaskDao
import com.example.todolist_compose.events.CreateTaskEvents
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import com.example.todolist_compose.repository.TaskRepository
import com.example.todolist_compose.state.TaskState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val taskRepository: TaskRepository
):ViewModel() {
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
            is CreateTaskEvents.SetStatus -> {
                state.update { it.copy(
                    taskStatus = event.taskStatus
                ) }
            }
            is CreateTaskEvents.SetExpirationDate -> {
                state.update { it.copy(
                    expirationDate = event.expirationDate
                ) }
            }
            CreateTaskEvents.CreateTask -> {
                val task = Task(
                    state.value.title,
                    state.value.description,
                    state.value.taskStatus,
                    state.value.expirationDate)
                viewModelScope.launch {
                    taskRepository.upsertTask(task)
                }
            }

            else -> {}
        }
    }

    fun setTask(id:Int){
        val task:Task
        val flowTask:Flow<Task> = emptyFlow()
        viewModelScope.launch {
             taskRepository.getTaskById(id)
             task:Task = flowTask.first()
        }
        flowTask.let {

            state.update { it.copy(
                id = task.id,
                title = task.title,
                description = task.description,
                taskStatus = task.status,
                expirationDate = task.expirationDate
            ) }
        }

    }
}