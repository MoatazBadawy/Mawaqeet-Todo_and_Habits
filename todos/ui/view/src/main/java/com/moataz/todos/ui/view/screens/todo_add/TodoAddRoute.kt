package com.moataz.todos.ui.view.screens.todo_add

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.moataz.todos.ui.view.navigation.Screen

fun NavGraphBuilder.todoAddRoute(navController: NavController) {
    dialog(
        route = Screen.TodoAdd.route,
        content = {
            TodoAddingDialogScreen(
                onDismiss = { navController.popBackStack() },
            )
        },
    )
}

fun NavController.navigateToAddTodo() {
    navigate(Screen.TodoAdd.route)
}
