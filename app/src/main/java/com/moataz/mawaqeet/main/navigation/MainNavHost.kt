package com.moataz.mawaqeet.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreens.HabitsNavGraph.route
    ) {
        todosNavRoute()
        habitsNavRoute()
    }
}
