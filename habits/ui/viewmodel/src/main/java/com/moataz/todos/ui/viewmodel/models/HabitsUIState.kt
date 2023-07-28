package com.moataz.todos.ui.viewmodel.models

data class HabitsUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean? = null,
    val habits: List<HabitUI> = emptyList(),
)
