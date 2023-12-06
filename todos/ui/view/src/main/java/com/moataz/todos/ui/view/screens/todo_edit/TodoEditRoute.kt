package com.moataz.todos.ui.view.screens.todo_edit

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.moataz.todos.ui.view.navigation.Screen
import com.moataz.todos.ui.viewmodel.TodoDetailsArgs

fun NavGraphBuilder.todoEditRoute(navController: NavController) {
    dialog(
        route = "${Screen.TodoEdit.route}/{${TodoDetailsArgs.TODO_ID}}/{${TodoDetailsArgs.TODO_TITLE}}",
        arguments = listOf(
            navArgument(TodoDetailsArgs.TODO_ID) {
                type = NavType.LongType
            },
            navArgument(TodoDetailsArgs.TODO_TITLE) {
                type = NavType.StringType
            }
        )
    ) {
        TodoEditScreen(
            navController = navController,
            onDismiss = { navController.popBackStack() }
        )
    }
}

fun NavController.navigateToEditTodo(todoId: Long, todoTitle: String) {
    navigate("${Screen.TodoEdit.route}/$todoId/$todoTitle")
}
