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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.moataz.mawaqeet.R
import com.moataz.mawaqeet.main.navigation.MainNavHost
import com.moataz.mawaqeet.main.navigation.MainScreens
import com.moataz.todos.ui.view.theme.Black

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val selectedItem = remember { mutableStateOf(MainScreens.HabitsNavGraph.route) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                backgroundColor = Color(0xFFF8F8F8),
                contentColor = Color.Unspecified
            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_habits),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    selected = selectedItem.value == MainScreens.HabitsNavGraph.route,
                    selectedContentColor = Black,
                    unselectedContentColor = Color(0xFFABABAB),
                    onClick = {
                        if (selectedItem.value != MainScreens.HabitsNavGraph.route) {
                            selectedItem.value = MainScreens.HabitsNavGraph.route
                            navController.navigate(MainScreens.HabitsNavGraph.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_todos),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    selected = selectedItem.value == MainScreens.TodosNavGraph.route,
                    selectedContentColor = Black,
                    unselectedContentColor = Color(0xFFABABAB),
                    onClick = {
                        if (selectedItem.value != MainScreens.TodosNavGraph.route) {
                            selectedItem.value = MainScreens.TodosNavGraph.route
                            navController.navigate(MainScreens.TodosNavGraph.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                )
            }
        }
    ) {
        MainNavHost(navController)
    }
}
