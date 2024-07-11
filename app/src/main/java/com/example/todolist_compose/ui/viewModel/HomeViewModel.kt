package com.example.todolist_compose.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.todolist_compose.database.dao.TaskDao
import com.example.todolist_compose.events.HomeEvents
import com.example.todolist_compose.model.TaskStatus
import com.example.todolist_compose.repository.TaskRepository
import com.example.todolist_compose.state.HomeState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _status = MutableStateFlow(TaskStatus.TO_DO)

    private val _tasks = _status.flatMapLatest {taskStatus ->
        taskRepository.getTasksByStatus(taskStatus)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(HomeState())

    val state = combine(_state, _tasks, _status){state, tasks, status ->
        state.copy(
            tasks = tasks,
            selectedTaskState = status
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    fun onEvent(event:HomeEvents){
        when(event){
            is HomeEvents.SortTasks -> {
                _status.update { event.taskStatus }
            }
            is HomeEvents.DeleteTask -> {
                viewModelScope.launch {
                    taskRepository.deleteTask(event.task)
                }
            }
        }
    }
}