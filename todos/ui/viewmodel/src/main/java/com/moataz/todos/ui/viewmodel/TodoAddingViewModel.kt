package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.todos.domain.usecases.InsertTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAddingViewModel @Inject constructor(
    private val insertTodoUseCase: InsertTodoUseCase,
) : ViewModel() {

    val todoTitle = MutableStateFlow("")

    private fun insertTodo() {
        viewModelScope.launch {
            try {
                insertTodoUseCase(todoTitle.value)
                todoTitle.value = ""
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onAddTodoClicked() {
        if (todoTitle.value.isNotEmpty()) {
            insertTodo()
        }
    }
}
