package com.moataz.todos.data.local

import androidx.room.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TodosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoEntity: TodoEntity)

    @Query("DELETE FROM TODOS_TABLE WHERE id = :id")
    suspend fun deleteTodo(id: Long)

    @Query("SELECT * FROM TODOS_TABLE ORDER BY isCompleted ASC")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("UPDATE TODOS_TABLE SET title = :title WHERE id = :id")
    suspend fun updateTodoTitle(id: Long, title: String)

    @Query("UPDATE TODOS_TABLE SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateTodoCompleted(id: Long, isCompleted: Boolean)
}
