package com.ui.uistate

data class HabitsMainUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean? = null,
    val habits: List<HabitUIState> = emptyList(),
)