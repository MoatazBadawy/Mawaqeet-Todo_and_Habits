package com.moataz.habits.data.local

import androidx.room.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface HabitsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Query("DELETE FROM HABITS_TABLE WHERE id = :id")
    suspend fun deleteHabit(id: Long)

    @Query("SELECT * FROM HABITS_TABLE WHERE type = :type ORDER BY isCompleted ASC")
    fun getAllHabitsByType(type: String): Flow<List<HabitEntity>>

    @Query("SELECT * FROM HABITS_TABLE")
    suspend fun getAllHabits(): List<HabitEntity>

    @Query("UPDATE HABITS_TABLE SET name = :name WHERE id = :id")
    suspend fun updateHabitName(id: Long, name: String)

    @Query("UPDATE HABITS_TABLE SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateHabitCompleted(id: Long, isCompleted: Boolean)

    @Update
    suspend fun updateHabits(habits: List<HabitEntity>)
}
