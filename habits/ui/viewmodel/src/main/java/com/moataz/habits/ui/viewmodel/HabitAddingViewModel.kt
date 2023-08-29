package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.habits.domain.usecases.InsertHabitUseCase
import com.moataz.habits.ui.viewmodel.utils.HabitType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitAddingViewModel @Inject constructor(
    private val insertHabitUseCase: InsertHabitUseCase,
) : ViewModel() {

    private val habitType = MutableStateFlow(HabitType.SPIRITUALITY)
    val habitName = MutableStateFlow("")

    private val _isNameValid = MutableLiveData(true)
    val isNameValid: LiveData<Boolean> = _isNameValid

    private val _isCancelClicked = Channel<Boolean>()
    val isCancelClicked get() = _isCancelClicked.receiveAsFlow()

    private fun insertHabit() {
        viewModelScope.launch {
            insertHabitUseCase(habitName.value, habitType.value.pathName)
            clearFields()
        }
    }

    fun onAddHabitClicked() {
        if (habitName.value.isNotEmpty()) {
            insertHabit()
            onCloseDialogClick()
        } else {
            _isNameValid.value = false
        }
    }

    fun onChipChooseHabitType(type: HabitType) {
        habitType.value = type
    }

    private fun clearFields() {
        habitName.value = ""
        habitType.value = HabitType.SPIRITUALITY
        _isNameValid.value = true
    }

    fun onCloseDialogClick() {
        viewModelScope.launch {
            clearFields()
            _isCancelClicked.send(true)
        }
    }
}
