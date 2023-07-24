package com.domain.usecase

import com.domain.repository.HabitRepository
import java.util.*
import javax.inject.Inject

class UpdateHabitsAsNotCompletedAndNextResetUseCase @Inject constructor(
    private val repository: HabitRepository,
) {
    suspend operator fun invoke() {
        val currentTime = getCurrentTime()
        val nextResetTime = getNextResetTime()
        repository.updateHabits(
            repository.getAllHabits().filter { habitEntity ->
                habitEntity.nextResetDate.before(currentTime)
            }.map { habitEntity ->
                habitEntity.copy(isCompleted = false, nextResetDate = nextResetTime)
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
