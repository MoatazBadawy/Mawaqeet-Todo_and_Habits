package com.moataz.mawaqeet.di

import android.content.Context
import com.moataz.common.data.MawaqeetDatabase
import com.moataz.common.data.MawaqeetDatabaseFactory
import com.moataz.todos.data.local.HabitsDao
import com.moataz.todos.data.local.TodosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context,
    ): MawaqeetDatabase {
        return MawaqeetDatabaseFactory.roomDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideHabitsDao(database: MawaqeetDatabase): HabitsDao {
        return database.habitsDao()
    }

    @Singleton
    @Provides
    fun provideTodosDao(database: MawaqeetDatabase): TodosDao {
        return database.todosDao()
    }
}
