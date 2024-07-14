package com.example.todolist_compose.state

import com.example.todolist_compose.model.Task

data class SearchTaskState(
    val text:String = "",
    val tasks:List<Task> = emptyList()
)
