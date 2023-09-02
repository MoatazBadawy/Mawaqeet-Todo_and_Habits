package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import com.moataz.habits.domain.usecases.GetAllHabitsByTypeUseCase
import com.moataz.habits.domain.usecases.UpdateHabitCompletedUseCase
import com.moataz.habits.domain.usecases.UpdateHabitsAsNotCompletedAndNextResetUseCase
import com.moataz.habits.ui.viewmodel.mapper.toHabit
import com.moataz.habits.ui.viewmodel.mapper.toHabitUI
import com.moataz.habits.ui.viewmodel.models.HabitUI
import com.moataz.habits.ui.viewmodel.models.HabitsUIState
import com.moataz.habits.ui.viewmodel.utils.HabitType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val getAllHabitsByTypeUseCase:
    GetAllHabitsByTypeUseCase,
    private val updateHabitCompletedUseCase:
    UpdateHabitCompletedUseCase,
    private val updateHabitsAsNotCompletedAndNextResetUseCase:
    UpdateHabitsAsNotCompletedAndNextResetUseCase,
) : ViewModel(), HabitsClicksListener {

    private val _habitsUIState = MutableStateFlow(HabitsUIState())
    val habitsUIState: StateFlow<HabitsUIState> get() = _habitsUIState

    private val currentHabitType = MutableStateFlow(HabitType.SPIRITUALITY)

    private val _addHabitClickedEvent = Channel<Boolean>()
    val addHabitClickedEvent get() = _addHabitClickedEvent.receiveAsFlow()

    private val _editHabitLongClickedEvent = Channel<HabitUI>()
    val editHabitLongClickedEvent get() = _editHabitLongClickedEvent.receiveAsFlow()

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
            currentHabitType.flatMapLatest { habitType ->
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

    override fun updateHabitCompleted(habit: HabitUI, isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                updateHabitCompletedUseCase(habit.toHabit(), isCompleted)
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
        if (habitType != currentHabitType.value) {
            currentHabitType.value = habitType
        }
    }

    fun onAddHabitClicked() {
        viewModelScope.launch {
            _addHabitClickedEvent.send(true)
        }
    }

    override fun onEditHabitLongClicked(habit: HabitUI): Boolean {
        viewModelScope.launch {
            _editHabitLongClickedEvent.send(habit)
        }
        return false
    }
}
