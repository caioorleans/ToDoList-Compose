package com.example.todolist_compose

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist_compose.dao.TaskDao
import com.example.todolist_compose.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase:RoomDatabase() {
    abstract val dao:TaskDao
}