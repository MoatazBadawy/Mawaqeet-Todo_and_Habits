package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.entities.Todo
import com.moataz.todos.domain.repository.TodosRepository
import javax.inject.Inject

class InsertTodoUseCase @Inject constructor(
    private val repository: TodosRepository,
) {
    suspend operator fun invoke(todoTitle: String) {
        return repository.insertTodo(
            Todo(
                title = todoTitle,
                isCompleted = false,
            ),
        )
    }
}
