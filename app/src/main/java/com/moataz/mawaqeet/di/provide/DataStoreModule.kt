package com.moataz.mawaqeet.di.provide

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.core.datastore.DataStorePreferences
import com.core.datastore.DataStorePreferencesImpl
import com.moataz.mawaqeet.di.utils.DatabaseConstant.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStorePreference(dataStore: DataStore<Preferences>): DataStorePreferences {
        return DataStorePreferencesImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.preferencesDataStore
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        SHARED_PREFERENCES_NAME
    )
}