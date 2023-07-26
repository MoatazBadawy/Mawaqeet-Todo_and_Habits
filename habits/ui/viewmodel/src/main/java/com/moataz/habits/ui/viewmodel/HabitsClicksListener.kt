package com.moataz.habits.ui.viewmodel

import com.moataz.habits.ui.viewmodel.models.HabitUI

interface HabitsClicksListener {
    fun updateHabitCompleted(habit: HabitUI, isCompleted: Boolean)
    fun onEditHabitLongClicked(habit: HabitUI): Boolean
}
