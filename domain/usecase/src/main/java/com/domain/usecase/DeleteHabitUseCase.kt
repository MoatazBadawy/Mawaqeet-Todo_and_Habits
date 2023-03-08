package com.domain.usecase

import com.domain.mapper.toHabitEntity
import com.domain.model.HabitModel
import com.domain.repository.HabitRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habitModel: HabitModel) {
        habitRepository.deleteHabit(habitModel.toHabitEntity())
    }
}