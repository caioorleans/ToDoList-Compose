package com.example.todolist_compose.repository

import com.example.todolist_compose.database.dao.TaskDao
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import kotlinx.coroutines.flow.Flow

interface IUserRepository{
    suspend fun insertTask(task:Task)
    suspend fun updateTask(task:Task)
    suspend fun deleteTask(task: Task)
    fun getTaskById(id:Int):Flow<Task>
    fun getTaskLikeTitleOrDescription(text:String):Flow<List<Task>>
    fun getTasksByStatus(taskStatus: TaskStatus): Flow<List<Task>>
}

class TaskRepository(private val dao: TaskDao):IUserRepository {
    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return dao.deleteTask(task)
    }

    override fun getTaskById(id: Int): Flow<Task> {
        return dao.getTaskById(id)
    }

    override fun getTaskLikeTitleOrDescription(text: String): Flow<List<Task>> {
        return dao.getTaskLikeTitleOrDescription(text)
    }

    override fun getTasksByStatus(taskStatus: TaskStatus): Flow<List<Task>> {
        return dao.getTasksByStatus(taskStatus)
    }
}