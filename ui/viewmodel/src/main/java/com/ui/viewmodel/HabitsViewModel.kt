package com.ui.viewmodel

import androidx.lifecycle.*
import com.domain.usecase.GetAllHabitsByTypeUseCase
import com.domain.usecase.UpdateHabitCompletedUseCase
import com.domain.usecase.UpdateHabitsAsNotCompletedAndNextResetUseCase
import com.ui.mapper.toHabitModel
import com.ui.mapper.toHabitUIState
import com.ui.uistate.HabitUIState
import com.ui.uistate.HabitsMainUIState
import com.ui.viewmodel.utils.HabitType
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
    UpdateHabitsAsNotCompletedAndNextResetUseCase
) : ViewModel(), HabitsClicksListener {

    private val _habitsUIState = MutableStateFlow(HabitsMainUIState())
    val habitsUIState: StateFlow<HabitsMainUIState> get() = _habitsUIState

    private val currentHabitType = MutableStateFlow(HabitType.SPIRITUALITY)

    private val _addHabitClickedEvent = Channel<Boolean>()
    val addHabitClickedEvent get() = _addHabitClickedEvent.receiveAsFlow()

    private val _editHabitLongClickedEvent = Channel<HabitUIState>()
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
                            it.toHabitUIState()
                        }
                    )
                }
            }
        }
    }

    override fun updateHabitCompleted(habit: HabitUIState, isCompleted: Boolean) {
        viewModelScope.launch {
            updateHabitCompletedUseCase(habit.toHabitModel(), isCompleted)
        }
    }

    private fun updateHabitsAsNotCompletedEveryDayAtSpecificTime() {
        viewModelScope.launch {
            updateHabitsAsNotCompletedAndNextResetUseCase()
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

    override fun onEditHabitLongClicked(habit: HabitUIState): Boolean {
        viewModelScope.launch {
            _editHabitLongClickedEvent.send(habit)
        }
        return false
    }
}