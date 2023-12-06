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

    private val _showAddTodoDialog = Channel<Boolean>()
    val showAddTodoDialog = _showAddTodoDialog.receiveAsFlow()

    private val _editTodoLongClicked = Channel<TodoUI?>()
    val editTodoLongClicked get() = _editTodoLongClicked.receiveAsFlow()

    init {
        viewModelScope.launch {
            getAllTodos()
        }
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            try {
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
            } catch (e: Exception) {
                _todosUIState.update { todosUIState ->
                    todosUIState.copy(
                        isLoading = false,
                        isSuccessful = false,
                        isError = true,
                        todos = emptyList(),
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

    fun onDialogDismissed() {
        viewModelScope.launch {
            _showAddTodoDialog.send(false)
            _editTodoLongClicked.send(null)
        }
    }

    fun onAddTodoClicked() {
        viewModelScope.launch {
            _showAddTodoDialog.send(true)
        }
    }

    override fun onEditTodoLongClicked(todo: TodoUI): Boolean {
        viewModelScope.launch {
            _editTodoLongClicked.send(todo)
        }
        return false
    }
}
