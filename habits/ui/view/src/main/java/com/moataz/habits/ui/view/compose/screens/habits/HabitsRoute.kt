package com.moataz.habits.ui.view.compose.screens.habits

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moataz.habits.ui.view.compose.navigation.Screen

fun NavGraphBuilder.habitsRoute(navController: NavController) {
    composable(
        route = Screen.Habits.route,
        content = {
            HabitsScreen(
                navController = navController,
            )
        },
    )
}
