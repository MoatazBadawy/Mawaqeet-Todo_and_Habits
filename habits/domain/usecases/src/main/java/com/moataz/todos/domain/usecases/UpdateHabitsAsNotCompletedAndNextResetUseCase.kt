package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.repository.HabitsRepository
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class UpdateHabitsAsNotCompletedAndNextResetUseCase @Inject constructor(
    private val repository: HabitsRepository,
) {
    suspend operator fun invoke() {
        val currentTime = getCurrentTime()
        val nextResetTime = getNextResetTime()
        repository.updateHabits(
            repository.getAllHabits().filter { habit ->
                habit.nextResetDate.before(currentTime)
            }.map { habit ->
                habit.copy(isCompleted = false, nextResetDate = nextResetTime)
            },
        )
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
