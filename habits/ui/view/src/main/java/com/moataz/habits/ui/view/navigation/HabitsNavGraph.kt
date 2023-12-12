package com.moataz.habits.ui.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moataz.habits.ui.view.screens.habit_add.habitAddRoute
import com.moataz.habits.ui.view.screens.habit_edit.habitEditRoute
import com.moataz.habits.ui.view.screens.habits.habitsRoute

@Composable
fun HabitsNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Habits.route
    ) {
        habitsRoute(navController = navController)
        habitAddRoute(navController = navController)
        habitEditRoute(navController = navController)
    }
}
