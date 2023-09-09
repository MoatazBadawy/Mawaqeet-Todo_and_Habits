package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.GetAllTodosUseCase
import com.moataz.todos.domain.usecases.UpdateTodoCompletedUseCase
import com.moataz.todos.ui.viewmodel.mapper.toTodo
import com.moataz.todos.ui.viewmodel.mapper.toTodosUI
import com.moataz.todos.ui.viewmodel.models.TodoUI
import com.moataz.todos.ui.viewmodel.models.TodosUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val updateTodoCompletedUseCase: UpdateTodoCompletedUseCase,
) : ViewModel(), TodosClicksListener {

    private val _todosUIState = MutableStateFlow(TodosUIState())
    val todosUIState: StateFlow<TodosUIState> get() = _todosUIState

    private val _addTodoClickedEvent = Channel<Boolean>()
    val addTodoClickedEvent get() = _addTodoClickedEvent.receiveAsFlow()

    private val _editTodoLongClickedEvent = Channel<TodoUI>()
    val editTodoLongClickedEvent get() = _editTodoLongClickedEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            getAllTodos()
        }
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            getAllTodosUseCase().collectLatest { todos ->
                _todosUIState.update { todosUIState ->
                    todosUIState.copy(
                        isLoading = false,
                        isSuccessful = true,
                        isError = false,
                        todos = todos.toTodosUI(),
                    )
                }
            }
        }
    }

    override fun updateTodoCompleted(todo: TodoUI, isCompleted: Boolean) {
        viewModelScope.launch {
            updateTodoCompletedUseCase(todo.toTodo(), isCompleted)
        }
    }

    fun onAddTodoClicked() {
        viewModelScope.launch {
            _addTodoClickedEvent.send(true)
        }
    }

    override fun onEditTodoLongClicked(todo: TodoUI): Boolean {
        viewModelScope.launch {
            _editTodoLongClickedEvent.send(todo)
        }
        return false
    }
}
