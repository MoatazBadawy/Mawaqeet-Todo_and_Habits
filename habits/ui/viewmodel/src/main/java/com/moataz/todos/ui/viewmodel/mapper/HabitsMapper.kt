package com.moataz.todos.ui.viewmodel.mapper

import com.moataz.todos.domain.entities.Habit
import com.moataz.todos.ui.viewmodel.models.HabitUI

internal fun HabitUI.toHabit(): Habit {
    return Habit(name = name, type = type, isCompleted = isCompleted)
}

internal fun Habit.toHabitUI(): HabitUI {
    return HabitUI(name, type, isCompleted)
}
