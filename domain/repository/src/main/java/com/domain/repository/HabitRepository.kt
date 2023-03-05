package com.domain.repository

import com.core.database.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun insertHabit(habit: HabitEntity)
    suspend fun deleteHabit(habit: HabitEntity)
    suspend fun getAllHabits(): List<HabitEntity>
    fun getAllHabitsByType(habitType: String): Flow<List<HabitEntity>>
    suspend fun updateHabitByCompleted(habit: HabitEntity, isCompleted: Boolean)
    suspend fun updateHabitName(habit: HabitEntity, name: String)
    suspend fun updateHabits(habits: List<HabitEntity>)
}