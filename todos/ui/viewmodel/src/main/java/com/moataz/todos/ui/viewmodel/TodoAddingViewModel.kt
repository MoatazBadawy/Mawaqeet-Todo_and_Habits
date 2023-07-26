package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.InsertTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAddingViewModel @Inject constructor(
    private val insertTodoUseCase: InsertTodoUseCase,
) : ViewModel() {

    val todoTitle = MutableStateFlow("")

    private val _isTitleValid = MutableLiveData(true)
    val isTitleValid: LiveData<Boolean> = _isTitleValid

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    private fun insertTodo() {
        viewModelScope.launch {
            insertTodoUseCase(todoTitle.value)
            clearFields()
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

    private fun clearFields() {
        todoTitle.value = ""
        _isTitleValid.value = true
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            clearFields()
            _isCancelClicked.send(true)
        }
    }
}
