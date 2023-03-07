package com.ui.viewmodel

import com.ui.uistate.HabitUIState

interface HabitsClicksListener {
    fun updateHabitCompleted(habit: HabitUIState, isCompleted: Boolean)
    fun onEditHabitLongClicked(habit: HabitUIState): Boolean
}