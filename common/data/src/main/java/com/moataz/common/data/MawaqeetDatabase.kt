package com.moataz.common.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moataz.common.data.utils.DatabaseConstant.DATABASE_ASSET_PATH
import com.moataz.common.data.utils.DatabaseConstant.DATABASE_NAME
import com.moataz.common.data.utils.MIGRATION_NEW_VERSION
import com.moataz.common.data.utils.TypeConverter
import com.moataz.habits.data.local.HabitEntity
import com.moataz.habits.data.local.HabitsDao
import com.moataz.todos.data.local.TodoEntity
import com.moataz.todos.data.local.TodosDao

@Database(
    entities = [
        HabitEntity::class,
        TodoEntity::class,
    ],
    version = 2,
)
@TypeConverters(TypeConverter::class)
abstract class MawaqeetDatabase : RoomDatabase() {
    abstract fun habitsDao(): HabitsDao
    abstract fun todosDao(): TodosDao
}

object MawaqeetDatabaseFactory {
    fun roomDatabase(context: Context) = Room.databaseBuilder(
        context,
        MawaqeetDatabase::class.java,
        DATABASE_NAME,
    ).createFromAsset(DATABASE_ASSET_PATH)
        .addMigrations(MIGRATION_NEW_VERSION)
        .build()
}
