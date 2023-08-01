package com.moataz.todos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.todos.data.local.utils.EntityConstant.TODO_TABLE

@Entity(tableName = TODO_TABLE)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val isCompleted: Boolean,
)
