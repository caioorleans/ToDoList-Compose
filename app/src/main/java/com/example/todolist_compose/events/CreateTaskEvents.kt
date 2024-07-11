package com.example.todolist_compose.events

import com.example.todolist_compose.model.TaskStatus

sealed interface CreateTaskEvents {
    data class SetTitle(val title:String):CreateTaskEvents
    data class SetDescription(val description:String):CreateTaskEvents
    data class SetStatus(val taskStatus: TaskStatus):CreateTaskEvents
    data class SetExpirationDate(val expirationDate:Long):CreateTaskEvents
    data object CreateTask:CreateTaskEvents
    data object ClearValues:CreateTaskEvents
}