package com.moataz.habits.ui.viewmodel.mapper

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.ui.viewmodel.models.HabitUI

internal fun Habit.toHabitUI(): HabitUI {
    return HabitUI(id, name, type, isCompleted)
}
