package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.GetAllTodosUseCase
import com.moataz.todos.domain.usecases.UpdateTodoCompletedUseCase
import com.moataz.todos.ui.viewmodel.mapper.toTodosUI
import com.moataz.todos.ui.viewmodel.models.TodosUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val updateTodoCompletedUseCase: UpdateTodoCompletedUseCase,
) : ViewModel() {

    private val _todosUIState = MutableStateFlow(TodosUIState())
    val todosUIState: StateFlow<TodosUIState> get() = _todosUIState

    init {
        getAllTodos()
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

    private fun updateTodoCompleted(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            updateTodoCompletedUseCase(id, isCompleted)
        }
    }

    fun onTodoCheckedChanged(id: Long, isCompleted: Boolean) {
        updateTodoCompleted(id, isCompleted)
    }
}
