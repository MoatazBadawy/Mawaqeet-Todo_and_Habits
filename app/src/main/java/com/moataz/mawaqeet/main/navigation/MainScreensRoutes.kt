package com.moataz.mawaqeet.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moataz.habits.ui.view.navigation.HabitsNavGraph
import com.moataz.todos.ui.view.navigation.TodosNavGraph

fun NavGraphBuilder.todosNavRoute() {
    composable(
        route = MainScreens.TodosNavGraph.route,
        content = {
            TodosNavGraph()
        },
    )
}

fun NavGraphBuilder.habitsNavRoute() {
    composable(
        route = MainScreens.HabitsNavGraph.route,
        content = {
            HabitsNavGraph()
        },
    )
}

