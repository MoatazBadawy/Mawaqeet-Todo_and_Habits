package com.moataz.mawaqeet.di

import com.moataz.habits.data.repositories.HabitsRepositoryImpl
import com.moataz.habits.domain.repository.HabitsRepository
import com.moataz.todos.data.repositories.TodosRepositoryImpl
import com.moataz.todos.domain.repository.TodosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindHabitsRepository(
        habitsRepositoryImpl: HabitsRepositoryImpl,
    ): HabitsRepository

    @ViewModelScoped
    @Binds
    abstract fun bindTodosRepository(
        todosRepositoryImpl: TodosRepositoryImpl,
    ): TodosRepository
}
