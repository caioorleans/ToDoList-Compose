package com.example.todolist_compose.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_compose.model.Task
import com.example.todolist_compose.repository.TaskRepository
import com.example.todolist_compose.state.SearchTaskState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.w3c.dom.Text

@OptIn(ExperimentalCoroutinesApi::class)
class SearchTaskViewModel(
    private val taskRepository: TaskRepository
):ViewModel() {
    private val _textSearch = MutableStateFlow("")
    private val _tasks: Flow<List<Task>> = _textSearch.flatMapLatest { text ->
        if(text != "") taskRepository.getTaskLikeTitleOrDescription(text)
        else MutableStateFlow(emptyList<Task>())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(SearchTaskState())

    val state = combine(_state, _textSearch, _tasks) {state, textSearch, tasks ->
        state.copy(
            tasks = tasks,
            text = textSearch
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SearchTaskState())

    fun updateText(text: String){
        _textSearch.update { text }
    }
}