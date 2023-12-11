package com.moataz.habits.domain.repository

import com.moataz.habits.domain.entities.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    suspend fun insertHabit(habit: Habit)
    suspend fun deleteHabit(id: Long)
    suspend fun getAllHabits(): List<Habit>
    fun getAllHabitsByType(habitType: String): Flow<List<Habit>>
    suspend fun updateHabitByCompleted(id: Long, isCompleted: Boolean)
    suspend fun updateHabitName(id: Long, name: String)
    suspend fun updateHabits(habits: List<Habit>)
}
