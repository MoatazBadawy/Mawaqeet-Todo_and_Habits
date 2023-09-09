package com.moataz.todos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoEntity: TodoEntity)

    @Query("DELETE FROM TODO_TABLE WHERE id = :id")
    suspend fun deleteTodo(id: Long)

    @Query("SELECT * FROM TODO_TABLE ORDER BY isCompleted ASC")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("UPDATE TODO_TABLE SET title = :title WHERE id = :id")
    suspend fun updateTodoTitle(id: Long, title: String)

    @Query("UPDATE TODO_TABLE SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateTodoCompleted(id: Long, isCompleted: Boolean)
}
