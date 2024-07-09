package com.example.todolist_compose.state

import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val tasks: List<Task> = emptyList(),
    val selectedTaskState: TaskStatus = TaskStatus.TO_DO
)
