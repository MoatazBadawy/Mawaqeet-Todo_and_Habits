package com.moataz.todos.domain.entities

data class Todo(
    val id: Long,
    val title: String,
    val isCompleted: Boolean,
)
