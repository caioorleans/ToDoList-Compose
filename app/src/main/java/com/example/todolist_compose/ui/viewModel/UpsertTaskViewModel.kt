package com.example.todolist_compose.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_compose.events.UpsertTaskEvents
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import com.example.todolist_compose.repository.TaskRepository
import com.example.todolist_compose.state.TaskState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpsertTaskViewModel(
    private val taskRepository: TaskRepository
):ViewModel() {
    var state = MutableStateFlow(TaskState())
        private set

    fun onEvent(event:UpsertTaskEvents){
        viewModelScope.launch {
            when(event){
                UpsertTaskEvents.ClearValues -> {
                    state.update {it.copy(
                        title = "",
                        description = "",
                        taskStatus = TaskStatus.TO_DO,
                        expirationDate = System.currentTimeMillis()
                    )}
                }
                is UpsertTaskEvents.SetTitle -> {
                    state.update { it.copy(
                        title = event.title
                    ) }
                }
                is UpsertTaskEvents.SetDescription -> {
                    state.update {it.copy(
                        description = event.description
                    )}
                }
                is UpsertTaskEvents.SetStatus -> {
                    state.update { it.copy(
                        taskStatus = event.taskStatus
                    ) }
                }
                is UpsertTaskEvents.SetExpirationDate -> {
                    state.update { it.copy(
                        expirationDate = event.expirationDate
                    ) }
                }
                UpsertTaskEvents.CreateTask -> {
                    val task = Task(
                        state.value.title,
                        state.value.description,
                        state.value.taskStatus,
                        state.value.expirationDate)
                    withContext(Dispatchers.IO) {
                        taskRepository.insertTask(task)
                    }
                }
                UpsertTaskEvents.UpdateTask -> {
                    println(state.value)
                    val task = Task(
                        state.value.title,
                        state.value.description,
                        state.value.taskStatus,
                        state.value.expirationDate,
                        state.value.id
                    )
                    println(task)
                    withContext(Dispatchers.IO) {
                        taskRepository.updateTask(task)
                    }
                }

                is UpsertTaskEvents.SetTask -> {
                    withContext(Dispatchers.IO) {
                        taskRepository.getTaskById(event.id).collect{
                                task ->
                            state.update { it.copy(
                                id = task.id,
                                title = task.title,
                                description = task.description,
                                taskStatus = task.status,
                                expirationDate = task.expirationDate,
                                update = true
                            ) }
                        }
                    }
                }
            }
        }
    }
}