package com.moataz.todos.ui.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moataz.todos.ui.view.screens.todo_add.todoAddRoute
import com.moataz.todos.ui.view.screens.todo_edit.todoEditRoute
import com.moataz.todos.ui.view.screens.todos.todosRoute

@Composable
fun TodosNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Todos.route
    ) {
        todosRoute(navController = navController)
        todoAddRoute(navController = navController)
        todoEditRoute(navController = navController)
    }
}
