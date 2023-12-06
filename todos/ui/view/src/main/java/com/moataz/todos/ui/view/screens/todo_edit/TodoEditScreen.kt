package com.moataz.todos.ui.view.screens.todo_edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.theme.Black
import com.moataz.todos.ui.view.theme.Red
import com.moataz.todos.ui.viewmodel.TodoEditingViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TodoEditScreen(
    navController: NavController,
    viewModel: TodoEditingViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val todoTitle by viewModel.todoTitle.collectAsState()

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface(shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp))) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.edit_todo),
                    modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
                    style = MaterialTheme.typography.h6
                )
                OutlinedTextField(
                    value = todoTitle,
                    onValueChange = { viewModel.todoTitle.value = it },
                    label = { Text(stringResource(id = R.string.todo_name)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Black,
                        disabledBorderColor = Black,
                        cursorColor = Black,
                        textColor = Black,
                        focusedLabelColor = Black,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        TextButton(
                            onClick = {
                                viewModel.onUpdateTodoClicked()
                                onDismiss()
                            },
                            enabled = todoTitle.isNotEmpty()
                        ) {
                            Text(stringResource(id = R.string.update_habit))
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        TextButton(
                            onClick = {
                                onDismiss()
                            },
                        ) {
                            Text(
                                stringResource(id = R.string.cancel),
                                color = Black
                            )
                        }
                    }

                    TextButton(onClick = {
                        viewModel.onDeleteTodoClicked()
                        onDismiss()
                    }) {
                        Text(
                            stringResource(id = R.string.delete_habit),
                            color = Red
                        )
                    }
                }
            }
        }
    }
}
