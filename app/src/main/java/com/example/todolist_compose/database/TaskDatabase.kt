package com.example.todolist_compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist_compose.database.dao.TaskDao
import com.example.todolist_compose.model.Task

@Database(
    entities = [Task::class],
    version = 2
)
abstract class TaskDatabase:RoomDatabase() {
    abstract val dao: TaskDao
}