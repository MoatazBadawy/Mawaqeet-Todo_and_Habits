package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.DeleteTodoUseCase
import com.moataz.todos.domain.usecases.UpdateTodoTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoEditingViewModel @Inject constructor(
    private val updateTodoTitleUseCase: UpdateTodoTitleUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    state: SavedStateHandle,
) : ViewModel() {

    private val _todoArgs = TodoDetailsArgs(state)
    val todoTitle = MutableStateFlow("")

    init {
        initNavArgs()
    }

    private fun initNavArgs() {
        todoTitle.value = _todoArgs.todoTitle ?: ""
    }

    private fun updateTodoByTitle() {
        viewModelScope.launch {
            updateTodoTitleUseCase(_todoArgs.todoId!!, todoTitle.value)
        }
    }

    private fun deleteTodo() {
        viewModelScope.launch {
            deleteTodoUseCase(_todoArgs.todoId!!)
        }
    }

    fun onUpdateTodoClicked() {
        updateTodoByTitle()
    }

    fun onDeleteTodoClicked() {
        deleteTodo()
    }
}
