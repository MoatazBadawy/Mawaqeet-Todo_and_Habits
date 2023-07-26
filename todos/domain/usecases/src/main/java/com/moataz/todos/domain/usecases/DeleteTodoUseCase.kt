package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.entities.Todo
import com.moataz.todos.domain.repository.TodosRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val repository: TodosRepository,
) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo)
    }
}
