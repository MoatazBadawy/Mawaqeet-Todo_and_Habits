package com.moataz.todos.data.local

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.todos.data.local.utils.EntityConstant.TODOS_TABLE

@Keep
@Entity(tableName = TODOS_TABLE)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val isCompleted: Boolean,
)
