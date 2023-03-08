package com.domain.usecase

import com.domain.mapper.toHabitEntity
import com.domain.model.HabitModel
import com.domain.repository.HabitRepository
import javax.inject.Inject

class UpdateHabitCompletedUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habitModel: HabitModel, isCompleted: Boolean) {
        habitRepository.updateHabitByCompleted(habitModel.toHabitEntity(), isCompleted)
    }
}
