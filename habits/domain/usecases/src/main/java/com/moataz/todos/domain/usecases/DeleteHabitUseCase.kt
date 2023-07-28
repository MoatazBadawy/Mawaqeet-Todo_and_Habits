package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.entities.Habit
import com.moataz.todos.domain.repository.HabitsRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(habit: Habit) {
        habitsRepository.deleteHabit(habit)
    }
}
