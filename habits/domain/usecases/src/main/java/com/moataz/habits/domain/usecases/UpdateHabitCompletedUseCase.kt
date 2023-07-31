package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class UpdateHabitCompletedUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(habit: Habit, isCompleted: Boolean) {
        habitsRepository.updateHabitByCompleted(habit, isCompleted)
    }
}
