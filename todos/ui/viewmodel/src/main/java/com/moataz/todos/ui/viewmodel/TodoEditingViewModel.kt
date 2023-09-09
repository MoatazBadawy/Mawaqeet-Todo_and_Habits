package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.DeleteTodoUseCase
import com.moataz.todos.domain.usecases.UpdateTodoTitleUseCase
import com.moataz.todos.ui.viewmodel.mapper.toTodo
import com.moataz.todos.ui.viewmodel.models.TodoUI
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

    private val _todoArgs: TodoUI? = state["todoUI"]

    private val todo = MutableStateFlow(TodoUI())
    val todoTitle = MutableStateFlow("")

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    init {
        initializeNavigationArguments()
    }

    private fun initializeNavigationArguments() {
        _todoArgs?.let { receivedTodo ->
            todo.value = receivedTodo
            todoTitle.value = receivedTodo.title
        }
    }

    private fun updateTodoByTitle() {
        viewModelScope.launch {
            updateTodoTitleUseCase(todo.value.toTodo(), todoTitle.value)
        }
    }

    private fun deleteTodo() {
        viewModelScope.launch {
            deleteTodoUseCase(todo.value.toTodo())
        }
    }

    fun onUpdateTodoClicked() {
        updateTodoByTitle()
        onCloseDialogClick()
    }

    fun onDeleteTodoClicked() {
        deleteTodo()
        onCloseDialogClick()
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            _isCancelClicked.send(true)
        }
    }
}
