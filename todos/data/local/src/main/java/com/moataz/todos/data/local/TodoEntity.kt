package com.moataz.todos.data.local

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.todos.data.local.utils.EntityConstant.TODO_TABLE

@Keep
@Entity(tableName = TODO_TABLE)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val isCompleted: Boolean,
)
