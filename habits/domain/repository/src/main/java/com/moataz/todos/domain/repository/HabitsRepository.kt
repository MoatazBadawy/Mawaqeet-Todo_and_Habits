package com.moataz.todos.domain.repository

import com.moataz.todos.domain.entities.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    suspend fun insertHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun getAllHabits(): List<Habit>
    fun getAllHabitsByType(habitType: String): Flow<List<Habit>>
    suspend fun updateHabitByCompleted(habit: Habit, isCompleted: Boolean)
    suspend fun updateHabitName(habit: Habit, name: String)
    suspend fun updateHabits(habits: List<Habit>)
}
