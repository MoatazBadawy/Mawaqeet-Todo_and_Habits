package com.moataz.mawaqeet.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moataz.mawaqeet.R
import com.moataz.mawaqeet.main.navigation.MainNavHost
import com.moataz.mawaqeet.main.navigation.MainScreens
import com.moataz.todos.ui.view.theme.Black

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(MainScreens.HabitsNavGraph.route, MainScreens.TodosNavGraph.route)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                backgroundColor = Color(0xFFF8F8F8),
                contentColor = Color.Unspecified
            ) {
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(
                                    id = if (screen == MainScreens.HabitsNavGraph.route)
                                        R.drawable.ic_habits else R.drawable.ic_todos
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                            )
                        },
                        selected = currentRoute == screen,
                        selectedContentColor = Black,
                        unselectedContentColor = Color(0xFFABABAB),
                        onClick = {
                            if (currentRoute != screen) {
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                    )
                }
            }
        }
    ) {
        MainNavHost(navController)
    }
}

/**
 * /\     /\
 *{  `---'  }
 *{  O   O  }
 *  >  V  <
 * `-.....-'
 *   "meow"
 */
