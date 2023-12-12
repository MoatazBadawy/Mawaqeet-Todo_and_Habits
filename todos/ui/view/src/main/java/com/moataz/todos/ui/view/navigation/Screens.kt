package com.moataz.todos.ui.view.navigation

sealed class Screen(val route: String) {
    object Todos : Screen("todos")
    object TodoAdd : Screen("add_todo")
    object TodoEdit : Screen("edit_todo")
}
