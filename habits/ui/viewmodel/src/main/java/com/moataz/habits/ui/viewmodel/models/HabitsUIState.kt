package com.moataz.habits.ui.viewmodel.models

data class HabitsUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean = false,
    val habits: List<HabitUI> = emptyList(),
)
