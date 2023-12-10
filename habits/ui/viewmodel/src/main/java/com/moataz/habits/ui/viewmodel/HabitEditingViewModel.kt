package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.habits.domain.usecases.DeleteHabitUseCase
import com.moataz.habits.domain.usecases.UpdateHabitNameUseCase
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

    private val _habitArgs = HabitDetailsArgs(state)
    val habitTitle = MutableStateFlow("")

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    init {
        initNavArgs()
    }

    private fun initNavArgs() {
        habitTitle.value = _habitArgs.habitTitle ?: ""
    }

    private fun updateHabitByName() {
        viewModelScope.launch {
            updateHabitNameUseCase(_habitArgs.habitId!!, habitTitle.value)
        }
    }

    private fun deleteHabit() {
        viewModelScope.launch {
            deleteHabitUseCase(_habitArgs.habitId!!)
        }
    }

    fun onUpdateHabitClicked() {
        updateHabitByName()
    }

    fun onDeleteHabitClicked() {
        deleteHabit()
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            _isCancelClicked.send(true)
        }
    }
}
