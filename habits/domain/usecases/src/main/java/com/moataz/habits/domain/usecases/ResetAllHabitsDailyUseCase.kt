package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

/**
 * Use case for resetting all habits to 'not completed' at a specific time each day.
 *
 * This is designed to allow users to repeat their habits every day. The reset occurs at 3:00 AM dynamically
 * by default. All habits are marked as 'not completed' and their `nextResetDate` is set to the next day's
 * specific reset time.
 */
class ResetAllHabitsDailyUseCase @Inject constructor(
    private val repository: HabitsRepository,
) {
    suspend operator fun invoke() {
        val habitsToReset = getHabitsToReset()
        val updatedHabits = resetHabits(habitsToReset)
        repository.updateHabits(updatedHabits)
    }

    private suspend fun getHabitsToReset(): List<Habit> {
        val currentTime = getCurrentTime()
        return repository.getAllHabits().filter { habit ->
            habit.nextResetDate.before(currentTime)
        }
    }

    private fun resetHabits(habits: List<Habit>): List<Habit> {
        val nextResetTime = getNextResetTime()
        return habits.map { habit ->
            habit.copy(isCompleted = false, nextResetDate = nextResetTime)
        }
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }

    private fun getNextResetTime(): Date {
        return Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 3)
        }.time
    }
}
