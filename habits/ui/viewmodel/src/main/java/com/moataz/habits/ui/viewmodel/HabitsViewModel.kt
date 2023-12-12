package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.habits.domain.usecases.GetAllHabitsByTypeUseCase
import com.moataz.habits.domain.usecases.ResetAllHabitsDailyUseCase
import com.moataz.habits.domain.usecases.UpdateHabitCompletedUseCase
import com.moataz.habits.ui.viewmodel.mapper.toHabitUI
import com.moataz.habits.ui.viewmodel.models.HabitsUIState
import com.moataz.habits.ui.viewmodel.utils.HabitType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val getAllHabitsByTypeUseCase:
    GetAllHabitsByTypeUseCase,
    private val updateHabitCompletedUseCase:
    UpdateHabitCompletedUseCase,
    private val updateHabitsAsNotCompletedAndNextResetUseCase:
    ResetAllHabitsDailyUseCase,
) : ViewModel() {

    private val _habitsUIState = MutableStateFlow(HabitsUIState())
    val habitsUIState: StateFlow<HabitsUIState> get() = _habitsUIState

    private val _currentHabitType = MutableStateFlow(HabitType.SPIRITUALITY)
    val currentHabitType get() = _currentHabitType.asStateFlow()

    init {
        viewModelScope.launch {
            updateHabitsAsNotCompletedEveryDayAtSpecificTime()
            delay(400)
            getAllHabitsByType()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getAllHabitsByType() {
        viewModelScope.launch {
            _currentHabitType.flatMapLatest { habitType ->
                getAllHabitsByTypeUseCase(habitType.pathName)
            }.collectLatest { habitsModel ->
                _habitsUIState.update { habitsMainUiState ->
                    habitsMainUiState.copy(
                        isLoading = false,
                        isSuccessful = true,
                        isError = false,
                        habits = habitsModel.map {
                            it.toHabitUI()
                        },
                    )
                }
            }.runCatching {
                _habitsUIState.update { habitsMainUiState ->
                    habitsMainUiState.copy(
                        isLoading = false,
                        isSuccessful = false,
                        isError = true,
                        habits = emptyList(),
                    )
                }
            }
        }
    }

    fun updateHabitCompleted(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                updateHabitCompletedUseCase(id, isCompleted)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateHabitsAsNotCompletedEveryDayAtSpecificTime() {
        viewModelScope.launch {
            try {
                updateHabitsAsNotCompletedAndNextResetUseCase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onChipTypeClicked(habitType: HabitType) {
        if (habitType != _currentHabitType.value) {
            _currentHabitType.value = habitType
        }
    }
}
