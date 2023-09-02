package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.habits.domain.usecases.DeleteHabitUseCase
import com.moataz.habits.domain.usecases.UpdateHabitNameUseCase
import com.moataz.habits.ui.viewmodel.mapper.toHabit
import com.moataz.habits.ui.viewmodel.models.HabitUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitEditingViewModel @Inject constructor(
    private val updateHabitNameUseCase: UpdateHabitNameUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    state: SavedStateHandle,
) : ViewModel() {

    private val _habitArgs: HabitUI? = state["habitUI"]

    private val habit = MutableStateFlow(HabitUI())
    val habitName = MutableStateFlow("")

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    init {
        initializeNavigationArguments()
    }

    private fun initializeNavigationArguments() {
        _habitArgs?.let { receivedHabit ->
            habit.value = receivedHabit
            habitName.value = receivedHabit.name
        }
    }

    private fun updateHabitByName() {
        viewModelScope.launch {
            updateHabitNameUseCase(habit.value.toHabit(), habitName.value)
        }
    }

    private fun deleteHabit() {
        viewModelScope.launch {
            deleteHabitUseCase(habit.value.toHabit())
        }
    }

    fun onUpdateHabitClicked() {
        updateHabitByName()
        onCloseDialogClick()
    }

    fun onDeleteHabitClicked() {
        deleteHabit()
        onCloseDialogClick()
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            _isCancelClicked.send(true)
        }
    }
}
