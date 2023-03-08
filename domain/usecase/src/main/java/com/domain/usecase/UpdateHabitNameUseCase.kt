package com.domain.usecase

import com.domain.mapper.toHabitEntity
import com.domain.model.HabitModel
import com.domain.repository.HabitRepository
import javax.inject.Inject

class UpdateHabitNameUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habitModel: HabitModel, name: String) {
        habitRepository.updateHabitName(habitModel.toHabitEntity(), name)
    }
}