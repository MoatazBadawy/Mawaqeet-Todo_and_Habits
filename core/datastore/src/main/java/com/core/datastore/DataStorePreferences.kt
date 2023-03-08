package com.core.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePreferences {
    fun readBoolean(key: String): Flow<Boolean?>
    suspend fun writeBoolean(key: String, value: Boolean)
    fun readString(key: String): String?
    suspend fun writeString(key: String, value: String)
    fun readInt(key: String): Int?
    suspend fun writeInt(key: String, value: Int)
}