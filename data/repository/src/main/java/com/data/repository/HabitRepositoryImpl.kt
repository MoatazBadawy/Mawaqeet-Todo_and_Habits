package com.data.repository

import com.core.database.dao.HabitDao
import com.core.database.entity.HabitEntity
import com.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val dao: HabitDao
) : HabitRepository {

    override suspend fun insertHabit(habit: HabitEntity) {
        dao.insertHabit(habit)
    }

    override suspend fun getAllHabits(): List<HabitEntity> {
        return dao.getAllHabits()
    }

    override fun getAllHabitsByType(
        habitType: String
    ): Flow<List<HabitEntity>> {
        return dao.getAllHabitsByType(habitType)
    }

    override suspend fun updateHabitByCompleted(habit: HabitEntity, isCompleted: Boolean) {
        dao.updateHabitCompleted(habit.id, isCompleted)
    }

    override suspend fun updateHabitName(habit: HabitEntity, name: String) {
        dao.updateHabitName(habit.id, name)
    }

    override suspend fun updateHabits(habits: List<HabitEntity>) {
        dao.updateHabits(habits)
    }

    override suspend fun deleteHabit(habit: HabitEntity) {
        dao.deleteHabit(habit.id)
    }
}