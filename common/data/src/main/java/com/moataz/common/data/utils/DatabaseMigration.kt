package com.moataz.common.data.utils // ktlint-disable filename

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moataz.common.data.utils.DatabaseConstant.CREATE_TODO_TABLE_QUERY

object MIGRATION_NEW_VERSION : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(CREATE_TODO_TABLE_QUERY)
    }
}
