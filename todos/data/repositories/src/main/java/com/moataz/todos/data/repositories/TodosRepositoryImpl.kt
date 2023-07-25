package com.moataz.todos.data.repositories

import com.moataz.todos.data.local.TodosDao
import com.moataz.todos.data.repositories.mapper.toTodoEntity
import com.moataz.todos.data.repositories.mapper.toTodos
import com.moataz.todos.domain.entities.Todo
import com.moataz.todos.domain.repository.TodosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodosRepositoryImpl @Inject constructor(
    private val dao: TodosDao,
) : TodosRepository {
    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo.toTodoEntity())
    }

    override suspend fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos().map { todosEntities ->
            todosEntities.toTodos()
        }
    }

    override suspend fun updateTodoTitle(todo: Todo, title: String) {
        dao.updateTodoTitle(todo.id, title)
    }

    override suspend fun updateTodoByCompleted(todo: Todo, isCompleted: Boolean) {
        dao.updateTodoCompleted(todo.id, isCompleted)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo.id)
    }
}
