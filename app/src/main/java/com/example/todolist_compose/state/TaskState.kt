package com.example.todolist_compose.state

import com.example.todolist_compose.model.TaskStatus

data class TaskState(
    val id:Int? = null,
    val title:String = "",
    val description:String = "",
    val taskStatus: TaskStatus = TaskStatus.TO_DO,
    val expirationDate:Long = 0,
    val update:Boolean = false
)
