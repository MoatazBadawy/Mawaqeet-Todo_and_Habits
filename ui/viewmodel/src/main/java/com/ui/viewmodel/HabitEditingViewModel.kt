package com.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import com.domain.usecase.DeleteHabitUseCase
import com.domain.usecase.UpdateHabitNameUseCase
import com.ui.mapper.toHabitModel
import com.ui.uistate.HabitUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitEditingViewModel @Inject constructor(
    private val updateHabitNameUseCase: UpdateHabitNameUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase
) : ViewModel() {

    private val _navArgs = MutableLiveData<NavArgs>()

    val habit = MutableStateFlow(HabitUIState())
    val habitName = MutableStateFlow("")

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    fun setupNavArgs(navArgs: NavArgs) {
        _navArgs.postValue(navArgs)
    }

    private fun updateHabitByName() {
        viewModelScope.launch {
            updateHabitNameUseCase(habit.value.toHabitModel(), habitName.value)
        }
    }

    private fun deleteHabit() {
        viewModelScope.launch {
            deleteHabitUseCase(habit.value.toHabitModel())
            clearFields()
            onCloseDialogClick()
        }
    }

    fun onUpdateHabitClicked() {
        updateHabitByName()
        clearFields()
        onCloseDialogClick()
    }

    fun onDeleteHabitClicked() {
        deleteHabit()
        clearFields()
        onCloseDialogClick()
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            _isCancelClicked.send(true)
        }
    }

    private fun clearFields() {
        habitName.value = ""
    }
}