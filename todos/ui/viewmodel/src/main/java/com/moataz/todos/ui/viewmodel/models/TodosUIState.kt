package com.moataz.todos.ui.viewmodel.models

data class TodosUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean = false,
    val todos: List<TodoUI> = emptyList(),
)
