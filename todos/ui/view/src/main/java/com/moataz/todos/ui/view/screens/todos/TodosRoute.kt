package com.moataz.todos.ui.view.screens.todos

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moataz.todos.ui.view.navigation.Screen

fun NavGraphBuilder.todosRoute(navController: NavController) {
    composable(
        route = Screen.Todos.route,
        content = {
            TodosScreen(
                navController = navController,
            )
        },
    )
}
