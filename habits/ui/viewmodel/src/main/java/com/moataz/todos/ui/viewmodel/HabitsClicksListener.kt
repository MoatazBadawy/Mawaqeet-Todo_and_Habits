package com.moataz.todos.ui.viewmodel

import com.moataz.todos.ui.viewmodel.models.HabitUI

interface HabitsClicksListener {
    fun updateHabitCompleted(habit: HabitUI, isCompleted: Boolean)
    fun onEditHabitLongClicked(habit: HabitUI): Boolean
}
