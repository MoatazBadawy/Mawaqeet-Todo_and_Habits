package com.moataz.habits.data.repositories

import com.moataz.habits.data.local.HabitsDao
import com.moataz.habits.data.repositories.mapper.toHabitEntity
import com.moataz.habits.data.repositories.mapper.toHabits
import com.moataz.habits.data.repositories.mapper.toHabitsEntities
import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitsRepositoryImpl @Inject constructor(
    private val dao: HabitsDao,
) : HabitsRepository {

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toHabitEntity())
    }

    override suspend fun getAllHabits(): List<Habit> {
        return dao.getAllHabits().toHabits()
    }

    override fun getAllHabitsByType(
        habitType: String,
    ): Flow<List<Habit>> {
        return dao.getAllHabitsByType(habitType).map { habitsEntities ->
            habitsEntities.toHabits()
        }
    }

    override suspend fun updateHabitByCompleted(habit: Habit, isCompleted: Boolean) {
        dao.updateHabitCompleted(habit.id, isCompleted)
    }

    override suspend fun updateHabitName(id: Long, name: String) {
        dao.updateHabitName(id, name)
    }

    override suspend fun updateHabits(habits: List<Habit>) {
        dao.updateHabits(habits.toHabitsEntities())
    }

    override suspend fun deleteHabit(id: Long) {
        dao.deleteHabit(id)
    }
}
