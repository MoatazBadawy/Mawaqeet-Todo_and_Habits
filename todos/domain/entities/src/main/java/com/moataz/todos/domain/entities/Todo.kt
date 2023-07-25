package com.moataz.todos.domain.entities

data class Todo(
    val id: Long = 0,
    val title: String,
    val isCompleted: Boolean,
)
