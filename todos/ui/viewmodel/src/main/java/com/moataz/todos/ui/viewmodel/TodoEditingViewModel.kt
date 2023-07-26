package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
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
) : ViewModel() {

    private val _navArgs = MutableLiveData<NavArgs>()

    val todo = MutableStateFlow(TodoUI())
    val todoTitle = MutableStateFlow("")

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    fun setupNavArgs(navArgs: NavArgs) {
        _navArgs.postValue(navArgs)
    }

    private fun updateTodoByTitle() {
        viewModelScope.launch {
            updateTodoTitleUseCase(todo.value.toTodo(), todoTitle.value)
        }
    }

    private fun deleteTodo() {
        viewModelScope.launch {
            deleteTodoUseCase(todo.value.toTodo())
            clearFields()
            onCloseDialogClick()
        }
    }

    fun onUpdateTodoClicked() {
        updateTodoByTitle()
        clearFields()
        onCloseDialogClick()
    }

    fun onDeleteTodoClicked() {
        deleteTodo()
        clearFields()
        onCloseDialogClick()
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            _isCancelClicked.send(true)
        }
    }

    private fun clearFields() {
        todoTitle.value = ""
    }
}
