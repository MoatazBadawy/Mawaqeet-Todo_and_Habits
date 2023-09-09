package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.InsertTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAddingViewModel @Inject constructor(
    private val insertTodoUseCase: InsertTodoUseCase,
) : ViewModel() {

    val todoTitle = MutableStateFlow("")

    private val _isTitleValid = MutableStateFlow(true)
    val isTitleValid get() = _isTitleValid.asStateFlow()

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    private fun insertTodo() {
        viewModelScope.launch {
            try {
                insertTodoUseCase(todoTitle.value)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onAddTodoClicked() {
        if (todoTitle.value.isNotEmpty()) {
            insertTodo()
            onCloseDialogClick()
        } else {
            _isTitleValid.value = false
        }
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            _isCancelClicked.send(true)
        }
    }
}
