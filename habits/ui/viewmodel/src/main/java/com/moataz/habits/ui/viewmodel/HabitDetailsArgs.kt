package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.SavedStateHandle

class HabitDetailsArgs(savedStateHandle: SavedStateHandle) {

    val habitTitle = savedStateHandle.get<String>(HABIT_TITLE)
    val habitId = savedStateHandle.get<Long>(HABIT_ID)
    val habitType = savedStateHandle.get<String>(HABIT_TYPE)

    companion object {
        const val HABIT_TITLE = "habitTitle"
        const val HABIT_ID = "habitId"
        const val HABIT_TYPE = "habitType"
    }
}
