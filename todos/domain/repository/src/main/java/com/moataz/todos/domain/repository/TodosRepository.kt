package com.moataz.todos.domain.repository

import com.moataz.todos.domain.entities.Todo
import kotlinx.coroutines.flow.Flow

interface TodosRepository {
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(id: Long)
    suspend fun getAllTodos(): Flow<List<Todo>>
    suspend fun updateTodoTitle(id: Long, title: String)
    suspend fun updateTodoByCompleted(todo: Todo, isCompleted: Boolean)
}
