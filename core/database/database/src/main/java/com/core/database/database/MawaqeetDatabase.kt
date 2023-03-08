package com.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.database.dao.HabitDao
import com.core.database.database.utils.DateConverter
import com.core.database.entity.HabitEntity

@Database(entities = [HabitEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MawaqeetDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}