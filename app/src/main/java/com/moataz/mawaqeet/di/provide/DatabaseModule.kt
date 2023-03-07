package com.moataz.mawaqeet.di.provide

import android.content.Context
import androidx.room.Room
import com.core.database.dao.HabitDao
import com.core.database.database.MawaqeetDatabase
import com.moataz.mawaqeet.di.utils.DatabaseConstant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): MawaqeetDatabase {
        return Room.databaseBuilder(
            appContext,
            MawaqeetDatabase::class.java,
            DATABASE_NAME
        ).createFromAsset("database/mawaqeet.db").build()
    }

    @Singleton
    @Provides
    fun provideHabitDao(database: MawaqeetDatabase): HabitDao {
        return database.habitDao()
    }
}