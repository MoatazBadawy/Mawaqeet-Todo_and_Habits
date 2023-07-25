package com.moataz.habits.data.local

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.habits.data.local.utils.EntityConstant.HABITS_TABLE
import com.moataz.habits.data.local.utils.EntityConstant.NEXT_RESET_DATE_HABIT
import java.util.Date

@Keep
@Entity(tableName = HABITS_TABLE)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String,
    val isCompleted: Boolean,
    val nextResetDate: Date = NEXT_RESET_DATE_HABIT,
)
