package com.moataz.habits.ui.view.compose.navigation

sealed class Screen(val route: String) {
    object Habits : Screen("habits")
    object HabitAdd : Screen("add_habit")
    object HabitEdit : Screen("edit_habit")
}
