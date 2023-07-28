package com.moataz.todos.data.repositories

import com.moataz.todos.data.local.HabitsDao
import com.moataz.todos.data.repositories.mapper.toHabitEntity
import com.moataz.todos.data.repositories.mapper.toHabits
import com.moataz.todos.data.repositories.mapper.toHabitsEntities
import com.moataz.todos.domain.entities.Habit
import com.moataz.todos.domain.repository.HabitsRepository
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

    override suspend fun updateHabitName(habit: Habit, name: String) {
        dao.updateHabitName(habit.id, name)
    }

    override suspend fun updateHabits(habits: List<Habit>) {
        dao.updateHabits(habits.toHabitsEntities())
    }

    override suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(habit.id)
    }
}
