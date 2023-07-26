package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class InsertHabitUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(habitName: String, habitType: String) {
        return habitsRepository.insertHabit(
            Habit(
                name = habitName,
                type = habitType,
                isCompleted = false,
            ),
        )
    }
}
