package com.moataz.habits.domain.usecases

import com.moataz.habits.domain.entities.Habit
import com.moataz.habits.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHabitsByTypeUseCase @Inject constructor(
    private val habitsRepository: HabitsRepository,
) {
    operator fun invoke(type: String): Flow<List<Habit>> {
        return habitsRepository.getAllHabitsByType(type)
    }
}
