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
import com.moataz.todos.ui.view.component.TodoError
import com.moataz.todos.ui.view.component.TodoItem
import com.moataz.todos.ui.view.component.TodosEmpty
import com.moataz.todos.ui.view.component.TodosToolbar
import com.moataz.todos.ui.view.screens.todo_add.navigateToAddTodo
import com.moataz.todos.ui.view.screens.todo_edit.navigateToEditTodo
import com.moataz.todos.ui.view.theme.White50
import com.moataz.todos.ui.viewmodel.TodosViewModel
import com.moataz.todos.ui.viewmodel.models.TodoUI

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

                    todosUIState.isSuccessful -> TodosSuccess(
                        todos = todosUIState.todos,
                        navigateToEditTodo = navController::navigateToEditTodo,
                        updateTodoCompleted = viewModel::onTodoCheckedChanged,
                    )

                    todosUIState.isError -> TodosError()
                }
            }
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodosSuccess(
    todos: List<TodoUI>,
    navigateToEditTodo: (Long, String) -> Unit,
    updateTodoCompleted: (Long, Boolean) -> Unit
) {
    if (todos.isNotEmpty()) {
        LazyColumn {
            items(todos, key = { it }) { todo ->
                TodoItem(
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 425,
                            )
                        ),
                    todo = todo,
                    onTodoLongClicked = navigateToEditTodo,
                    onTodoCheckedChange = updateTodoCompleted,
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

@Composable
fun TodosError() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TodoError()
    }
}
