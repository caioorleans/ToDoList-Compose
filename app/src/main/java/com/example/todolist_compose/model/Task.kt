package com.example.todolist_compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title:String,
    val description:String,
    val status: TaskStatus,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)