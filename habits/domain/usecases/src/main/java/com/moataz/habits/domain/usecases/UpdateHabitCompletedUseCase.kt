package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class UpdateHabitCompletedUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(id: Long, isCompleted: Boolean) {
        habitsRepository.updateHabitByCompleted(id, isCompleted)
    }
}
