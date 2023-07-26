package com.moataz.habits.data.repositories.mapper

import com.moataz.habits.data.local.HabitEntity
import com.moataz.habits.domain.entities.Habit

internal fun Habit.toHabitEntity(): HabitEntity {
    return HabitEntity(id, name, type, isCompleted, nextResetDate)
}

internal fun List<Habit>.toHabitsEntities(): List<HabitEntity> {
    return map { it.toHabitEntity() }
}

internal fun HabitEntity.toHabit(): Habit {
    return Habit(id, name, type, isCompleted, nextResetDate)
}

internal fun List<HabitEntity>.toHabits(): List<Habit> {
    return map { it.toHabit() }
}
