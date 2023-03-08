package com.domain.usecase

import com.core.database.entity.HabitEntity
import com.domain.repository.HabitRepository
import javax.inject.Inject

class InsertHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habitName: String, habitType: String) {
        return habitRepository.insertHabit(
            HabitEntity(
                name = habitName,
                type = habitType,
                isCompleted = false
            )
        )
    }
}