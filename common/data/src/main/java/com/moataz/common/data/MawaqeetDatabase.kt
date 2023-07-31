package com.moataz.common.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moataz.common.data.utils.DateConverter
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
@TypeConverters(DateConverter::class)
abstract class MawaqeetDatabase : RoomDatabase() {
    abstract fun habitsDao(): HabitsDao
    abstract fun todosDao(): TodosDao
}

object MawaqeetDatabaseFactory {
    fun roomDatabase(context: Context) = Room.databaseBuilder(
        context,
        MawaqeetDatabase::class.java,
        "mawaqeet.db",
    ).createFromAsset("database/mawaqeet.db").build()
}
