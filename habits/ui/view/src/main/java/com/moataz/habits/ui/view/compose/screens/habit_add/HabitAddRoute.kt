package com.moataz.habits.ui.view.compose.screens.habit_add

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.moataz.habits.ui.view.compose.navigation.Screen

fun NavGraphBuilder.habitAddRoute(navController: NavController) {
    dialog(
        route = Screen.HabitAdd.route,
        content = {
            HabitAddingDialogScreen(
                navController = navController,
                onDismiss = { navController.popBackStack() },
            )
        },
    )
}

fun NavController.navigateToAddHabit() {
    navigate(Screen.HabitAdd.route)
}
