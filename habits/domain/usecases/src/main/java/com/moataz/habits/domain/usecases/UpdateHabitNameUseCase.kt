package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class UpdateHabitNameUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(habit: Habit, name: String) {
        habitsRepository.updateHabitName(habit, name)
    }
}
