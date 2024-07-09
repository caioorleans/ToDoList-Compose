package com.example.todolist_compose.state

import com.example.todolist_compose.model.TaskStatus

data class TaskState(
    val title:String = "",
    val description:String = "",
    val taskStatus: TaskStatus = TaskStatus.TO_DO
)
