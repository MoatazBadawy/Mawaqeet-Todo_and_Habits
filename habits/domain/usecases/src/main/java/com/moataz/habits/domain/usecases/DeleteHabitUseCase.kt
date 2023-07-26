package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(habit: Habit) {
        habitsRepository.deleteHabit(habit)
    }
}
