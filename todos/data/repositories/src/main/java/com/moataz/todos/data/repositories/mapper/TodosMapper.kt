package com.moataz.todos.data.repositories.mapper

import com.moataz.todos.data.local.TodoEntity
import com.moataz.todos.domain.entities.Todo

internal fun Todo.toTodoEntity(): TodoEntity {
    return TodoEntity(id, title, isCompleted)
}

internal fun TodoEntity.toTodo(): Todo {
    return Todo(id, title, isCompleted)
}

internal fun List<TodoEntity>.toTodos(): List<Todo> {
    return map { it.toTodo() }
}
