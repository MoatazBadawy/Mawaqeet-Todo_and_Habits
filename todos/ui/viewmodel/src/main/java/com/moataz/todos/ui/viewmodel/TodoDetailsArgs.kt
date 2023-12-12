package com.moataz.todos.ui.viewmodel

import androidx.lifecycle.SavedStateHandle

class TodoDetailsArgs(savedStateHandle: SavedStateHandle) {

    val todoTitle = savedStateHandle.get<String>(TODO_TITLE)
    val todoId = savedStateHandle.get<Long>(TODO_ID)

    companion object {
        const val TODO_TITLE = "todoTitle"
        const val TODO_ID = "todoId"
    }
}
