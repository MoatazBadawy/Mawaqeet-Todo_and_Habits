package com.core.database.dao

import androidx.room.*
import com.core.database.entity.HabitEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Query("DELETE FROM HABIT_TABLE WHERE id = :id")
    suspend fun deleteHabit(id: Long)

    @Query("SELECT * FROM HABIT_TABLE WHERE type = :type ORDER BY isCompleted ASC")
    fun getAllHabitsByType(type: String): Flow<List<HabitEntity>>

    @Query("SELECT * FROM HABIT_TABLE")
    suspend fun getAllHabits(): List<HabitEntity>

    @Query("UPDATE HABIT_TABLE SET name = :name WHERE id = :id")
    suspend fun updateHabitName(id: Long, name: String)

    @Query("UPDATE HABIT_TABLE SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateHabitCompleted(id: Long, isCompleted: Boolean)

    @Update
    suspend fun updateHabits(habits: List<HabitEntity>)
}