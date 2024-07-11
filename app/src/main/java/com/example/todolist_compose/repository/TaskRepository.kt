package com.example.todolist_compose.repository

import com.example.todolist_compose.database.dao.TaskDao
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import kotlinx.coroutines.flow.Flow

interface IUserRepository{
    suspend fun upsertTask(task:Task)
    suspend fun deleteTask(task: Task)
    fun getTasksByStatus(taskStatus: TaskStatus): Flow<List<Task>>
}

class TaskRepository(private val dao: TaskDao):IUserRepository {
    override suspend fun upsertTask(task: Task) {
        return dao.upsertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return deleteTask(task)
    }

    override fun getTasksByStatus(taskStatus: TaskStatus): Flow<List<Task>> {
        return dao.getTasksByStatus(taskStatus)
    }
}