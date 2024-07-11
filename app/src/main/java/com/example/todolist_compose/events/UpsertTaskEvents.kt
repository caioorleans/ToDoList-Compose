package com.example.todolist_compose.events

import com.example.todolist_compose.model.TaskStatus

sealed interface UpsertTaskEvents {
    data class SetTitle(val title:String):UpsertTaskEvents
    data class SetDescription(val description:String):UpsertTaskEvents
    data class SetStatus(val taskStatus: TaskStatus):UpsertTaskEvents
    data class SetExpirationDate(val expirationDate:Long):UpsertTaskEvents
    data class SetTask(val id:Int):UpsertTaskEvents
    data object CreateTask:UpsertTaskEvents
    data object UpdateTask:UpsertTaskEvents
    data object ClearValues:UpsertTaskEvents
}