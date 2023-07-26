package com.moataz.todos.ui.viewmodel.mapper

import com.moataz.todos.domain.entities.Todo
import com.moataz.todos.ui.viewmodel.models.TodoUI

internal fun TodoUI.toTodo(): Todo {
    return Todo(title = title, isCompleted = isCompleted)
}

internal fun Todo.toTodoUI(): TodoUI {
    return TodoUI(title, isCompleted)
}

internal fun List<Todo>.toTodosUI(): List<TodoUI> {
    return map { it.toTodoUI() }
}
