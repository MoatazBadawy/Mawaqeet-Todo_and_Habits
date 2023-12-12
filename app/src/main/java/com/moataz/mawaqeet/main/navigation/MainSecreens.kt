package com.moataz.mawaqeet.main.navigation

sealed class MainScreens(val route: String) {
    data object TodosNavGraph : MainScreens("todos_nav_graph")
    data object HabitsNavGraph : MainScreens("habits_nav_graph")
}