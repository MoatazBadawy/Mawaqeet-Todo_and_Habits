package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import com.moataz.todos.domain.usecases.DeleteHabitUseCase
import com.moataz.todos.domain.usecases.UpdateHabitNameUseCase
import com.moataz.todos.ui.viewmodel.mapper.toHabit
import com.moataz.todos.ui.viewmodel.models.HabitUI
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
) : ViewModel() {

    private val _navArgs = MutableLiveData<NavArgs>()

    val habit = MutableStateFlow(HabitUI())
    val habitName = MutableStateFlow("")

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    fun setupNavArgs(navArgs: NavArgs) {
        _navArgs.postValue(navArgs)
    }

    private fun updateHabitByName() {
        viewModelScope.launch {
            updateHabitNameUseCase(habit.value.toHabit(), habitName.value)
        }
    }

    private fun deleteHabit() {
        viewModelScope.launch {
            deleteHabitUseCase(habit.value.toHabit())
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
