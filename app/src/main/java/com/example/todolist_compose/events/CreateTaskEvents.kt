package com.example.todolist_compose.events

import com.example.todolist_compose.model.TaskStatus

sealed interface CreateTaskEvents {
    data class SetTitle(val title:String):CreateTaskEvents
    data class SetDescription(val description:String):CreateTaskEvents
    data class setStatus(val taskStatus: TaskStatus):CreateTaskEvents
    data class setExpirationDate(val expirationDate:Long):CreateTaskEvents
    data object createTask:CreateTaskEvents
    data object ClearValues:CreateTaskEvents
    data object goToPreviousPage:CreateTaskEvents
}