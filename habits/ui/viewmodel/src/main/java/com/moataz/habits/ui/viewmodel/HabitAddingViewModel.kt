package com.moataz.habits.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.habits.domain.usecases.InsertHabitUseCase
import com.moataz.habits.ui.viewmodel.utils.HabitType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitAddingViewModel @Inject constructor(
    private val insertHabitUseCase: InsertHabitUseCase,
) : ViewModel() {

    private val _habitType = MutableStateFlow(HabitType.SPIRITUALITY)
    val habitType get() = _habitType.asStateFlow()

    val habitName = MutableStateFlow("")

    private fun insertHabit() {
        viewModelScope.launch {
            try {
                insertHabitUseCase(
                    habitName.value,
                    _habitType.value.pathName
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onAddHabitClicked() {
        if (habitName.value.isNotEmpty()) {
            insertHabit()
        }
    }

    fun onChipChooseHabitType(type: HabitType) {
        _habitType.value = type
    }
}
