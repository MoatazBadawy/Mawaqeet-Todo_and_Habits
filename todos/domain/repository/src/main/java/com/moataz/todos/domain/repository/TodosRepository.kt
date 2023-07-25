package com.moataz.todos.domain.repository

import com.moataz.todos.domain.entities.Todo
import kotlinx.coroutines.flow.Flow

interface TodosRepository {
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun getAllTodos(): Flow<List<Todo>>
    suspend fun updateTodoTitle(todo: Todo, title: String)
    suspend fun updateTodoByCompleted(todo: Todo, isCompleted: Boolean)
}
