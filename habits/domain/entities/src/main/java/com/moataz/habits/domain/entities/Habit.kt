package com.moataz.habits.domain.entities

import com.moataz.habits.domain.entities.utils.HabitsConstants.NEXT_RESET_DATE_HABIT
import java.util.Date

data class Habit(
    val id: Long = 0,
    val name: String,
    val type: String,
    val isCompleted: Boolean,
    val nextResetDate: Date = NEXT_RESET_DATE_HABIT,
)
