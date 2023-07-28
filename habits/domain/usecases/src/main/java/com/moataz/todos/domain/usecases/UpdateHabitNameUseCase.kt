package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.entities.Habit
import com.moataz.todos.domain.repository.HabitsRepository
import javax.inject.Inject

class UpdateHabitNameUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(habit: Habit, name: String) {
        habitsRepository.updateHabitName(habit, name)
    }
}
