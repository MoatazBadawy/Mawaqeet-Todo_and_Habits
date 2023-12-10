package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class UpdateHabitNameUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(id: Long, name: String) {
        habitsRepository.updateHabitName(id, name)
    }
}
