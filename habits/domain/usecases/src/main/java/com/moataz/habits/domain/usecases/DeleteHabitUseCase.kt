package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.repository.HabitsRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    suspend operator fun invoke(id: Long) {
        habitsRepository.deleteHabit(id)
    }
}
