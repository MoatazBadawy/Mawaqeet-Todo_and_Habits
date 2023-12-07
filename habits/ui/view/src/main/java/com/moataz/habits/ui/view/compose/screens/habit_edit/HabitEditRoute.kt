package com.moataz.habits.ui.view.compose.screens.habit_edit

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.moataz.habits.ui.view.compose.navigation.Screen
import com.moataz.habits.ui.viewmodel.HabitDetailsArgs

fun NavGraphBuilder.habitEditRoute(navController: NavController) {
    dialog(
        route = "${Screen.HabitEdit.route}/{${HabitDetailsArgs.HABIT_ID}}/" +
                "{${HabitDetailsArgs.HABIT_TITLE}}/{${HabitDetailsArgs.HABIT_TYPE}}",
        arguments = listOf(
            navArgument(HabitDetailsArgs.HABIT_ID) {
                type = NavType.LongType
            },
            navArgument(HabitDetailsArgs.HABIT_TITLE) {
                type = NavType.StringType
            },
            navArgument(HabitDetailsArgs.HABIT_TYPE) {
                type = NavType.StringType
            }
        )
    ) {
        HabitEditScreen(
            navController = navController,
            onDismiss = { navController.popBackStack() }
        )
    }
}

fun NavController.navigateToEditHabit(
    habitId: Long,
    habitTitle: String,
    habitType: String
) {
    navigate(
        "${Screen.HabitEdit.route}/$habitId/$habitTitle/$habitType"
    )
}
