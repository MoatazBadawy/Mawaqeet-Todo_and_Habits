package com.domain.usecase

import com.domain.mapper.toHabitModel
import com.domain.model.HabitModel
import com.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllHabitsByTypeUseCase @Inject constructor(
    private val habitRepository: HabitRepository,
) {
    operator fun invoke(type: String): Flow<List<HabitModel>> {
        return habitRepository.getAllHabitsByType(type).map {
            it.map { habitEntity ->
                habitEntity.toHabitModel()
            }
        }
    }
}