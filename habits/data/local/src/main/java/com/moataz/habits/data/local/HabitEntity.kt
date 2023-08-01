package com.moataz.habits.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.habits.data.local.utils.EntityConstant.HABIT_TABLE
import com.moataz.habits.data.local.utils.EntityConstant.NEXT_RESET_DATE_HABIT
import java.util.Date

@Entity(tableName = HABIT_TABLE)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String,
    val isCompleted: Boolean,
    val nextResetDate: Date = NEXT_RESET_DATE_HABIT,
)
