package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.moataz.todos.ui.viewmodel.models.TodoUI

class TodoDetailsArgs(savedStateHandle: SavedStateHandle) {

    val todoTitle = savedStateHandle.get<String>(TODO_TITLE)
    val todoId = savedStateHandle.get<Long>(TODO_ID)

    companion object {
        const val TODO_TITLE = "todoTitle"
        const val TODO_ID = "todoId"
    }
}
