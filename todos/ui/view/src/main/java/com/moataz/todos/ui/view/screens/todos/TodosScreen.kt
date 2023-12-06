package com.moataz.todos.ui.view.screens.todos

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.component.Loading
import com.moataz.todos.ui.view.component.TodoItem
import com.moataz.todos.ui.view.component.TodosEmpty
import com.moataz.todos.ui.view.component.TodosError
import com.moataz.todos.ui.view.component.TodosToolbar
import com.moataz.todos.ui.view.screens.todo_add.navigateToAddTodo
import com.moataz.todos.ui.view.screens.todo_edit.navigateToEditTodo
import com.moataz.todos.ui.view.theme.White50
import com.moataz.todos.ui.viewmodel.TodosViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TodosScreen(
    navController: NavController,
    viewModel: TodosViewModel = hiltViewModel(),
) {
    val todosUIState by viewModel.todosUIState.collectAsState()

    Scaffold(
        topBar = {
            TodosToolbar(
                title = stringResource(id = R.string.todos),
                onAddTodoClickedIcon = navController::navigateToAddTodo,
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White50)
                    .padding(top = 8.dp),
            ) {
                when {
                    todosUIState.isLoading -> Loading()

                    todosUIState.isSuccessful -> {
                        if (todosUIState.todos.isNotEmpty()) {
                            LazyColumn {
                                items(todosUIState.todos, key = { it }) { todo ->
                                    TodoItem(
                                        modifier = Modifier
                                            .animateItemPlacement(
                                                animationSpec = tween(
                                                    durationMillis = 425,
                                                )
                                            ),
                                        todo = todo,
                                        onTodoLongClicked = navController::navigateToEditTodo,
                                        onTodoCheckedChange = viewModel::updateTodoCompleted,
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                TodosEmpty()
                            }
                        }
                    }

                    todosUIState.isError -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            TodosError()
                        }
                    }
                }
            }
        },
    )
}
