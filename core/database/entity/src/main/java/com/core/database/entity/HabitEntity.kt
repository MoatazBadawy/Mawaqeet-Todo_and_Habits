package com.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.core.database.entity.utils.EntityConstant.HABIT_TABLE
import com.core.database.entity.utils.EntityConstant.NEXT_RESET_DATE_HABIT
import java.util.*

@Entity(tableName = HABIT_TABLE)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String,
    val isCompleted: Boolean,
    val nextResetDate: Date = NEXT_RESET_DATE_HABIT
)