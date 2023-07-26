package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.entities.Todo
import com.moataz.todos.domain.repository.TodosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodosUseCase @Inject constructor(
    private val repository: TodosRepository,
) {
    suspend operator fun invoke(): Flow<List<Todo>> {
        return repository.getAllTodos()
    }
}
