package com.example.todolist_compose.events

import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus

sealed interface HomeEvents {
    data class SortTasks(val taskStatus: TaskStatus):HomeEvents
    data class DeleteTask(val task: Task):HomeEvents
}