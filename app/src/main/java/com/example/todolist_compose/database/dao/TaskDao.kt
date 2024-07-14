package com.example.todolist_compose.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.model.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("select * from task where task.id = :id")
    fun getTaskById(id:Int):Flow<Task>

    @Query("select * from task where task.title like '%'||:text||'%' " +
            "or task.description like '%'||:text||'%'")
    fun getTaskLikeTitleOrDescription(text:String):Flow<List<Task>>

    @Query("select * from task where task.status = :status")
    fun getTasksByStatus(status: TaskStatus):Flow<List<Task>>
}