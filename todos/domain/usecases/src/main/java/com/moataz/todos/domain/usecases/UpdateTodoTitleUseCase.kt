package com.moataz.todos.domain.usecases

import com.moataz.todos.domain.repository.TodosRepository
import javax.inject.Inject

class UpdateTodoTitleUseCase @Inject constructor(
    private val repository: TodosRepository,
) {
    suspend operator fun invoke(id: Long, name: String) {
        repository.updateTodoTitle(id, name)
    }
}
