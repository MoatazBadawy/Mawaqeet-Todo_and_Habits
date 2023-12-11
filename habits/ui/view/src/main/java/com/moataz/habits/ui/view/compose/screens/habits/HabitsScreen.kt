package com.moataz.habits.ui.view.compose.screens.habits

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moataz.habits.ui.view.R
import com.moataz.habits.ui.view.compose.component.HabitError
import com.moataz.habits.ui.view.compose.component.HabitItem
import com.moataz.habits.ui.view.compose.component.HabitTypeChip
import com.moataz.habits.ui.view.compose.component.HabitsEmpty
import com.moataz.habits.ui.view.compose.component.HabitsToolbar
import com.moataz.habits.ui.view.compose.component.Loading
import com.moataz.habits.ui.view.compose.screens.habit_add.navigateToAddHabit
import com.moataz.habits.ui.view.compose.screens.habit_edit.navigateToEditHabit
import com.moataz.habits.ui.view.compose.theme.White50
import com.moataz.habits.ui.viewmodel.HabitsViewModel
import com.moataz.habits.ui.viewmodel.models.HabitUI
import com.moataz.habits.ui.viewmodel.utils.HabitType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HabitsScreen(
    navController: NavController,
    viewModel: HabitsViewModel = hiltViewModel(),
) {
    val habitsUIState by viewModel.habitsUIState.collectAsState()

    Scaffold(
        topBar = {
            HabitsToolbar(
                title = stringResource(id = R.string.habits),
                onAddHabitClickedIcon = navController::navigateToAddHabit,
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White50)
                    .padding(top = 8.dp),
            ) {
                Column {
                    HabitTypeChips(viewModel = viewModel)

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                    when {
                        habitsUIState.isLoading -> Loading()

                        habitsUIState.isSuccessful -> HabitsSuccess(
                            habits = habitsUIState.habits,
                            navigateToEditHabit = navController::navigateToEditHabit,
                            updateHabitCompleted = viewModel::updateHabitCompleted,
                        )

                        habitsUIState.isError -> HabitsError()
                    }
                }
            }
        },
    )
}

@Composable
fun HabitTypeChips(viewModel: HabitsViewModel) {
    val currentHabitType by viewModel.currentHabitType.collectAsState()

    Spacer(modifier = Modifier.padding(top = 4.dp))
    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
        items(HabitType.values().toList()) { habitType ->
            HabitTypeChip(
                text = habitType.displayName,
                checked = habitType == currentHabitType,
                onCheckedChange = { if (it) viewModel.onChipTypeClicked(habitType) }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HabitsSuccess(
    habits: List<HabitUI>,
    navigateToEditHabit: (Long, String, String) -> Unit,
    updateHabitCompleted: (HabitUI, Boolean) -> Unit
) {
    if (habits.isNotEmpty()) {
        LazyColumn {
            items(habits, key = { it }) { habit ->
                HabitItem(
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 425,
                            )
                        ),
                    habit = habit,
                    onHabitLongClicked = navigateToEditHabit,
                    onHabitCheckedChange = updateHabitCompleted,
                )
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HabitsEmpty()
        }
    }
}

@Composable
fun HabitsError() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HabitError()
    }
}
