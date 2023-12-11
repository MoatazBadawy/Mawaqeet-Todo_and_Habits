package com.moataz.habits.ui.view.screens.habit_add

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.moataz.habits.ui.view.navigation.Screen

fun NavGraphBuilder.habitAddRoute(navController: NavController) {
    dialog(
        route = Screen.HabitAdd.route,
        content = {
            HabitAddingDialogScreen(
                onDismiss = { navController.popBackStack() },
            )
        },
    )
}

fun NavController.navigateToAddHabit() {
    navigate(Screen.HabitAdd.route)
}
