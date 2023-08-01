package com.moataz.common.data.utils // ktlint-disable filename

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moataz.todos.data.local.utils.EntityConstant.TODO_TABLE

object MIGRATION_NEW_VERSION : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS $TODO_TABLE " +
                "(id INTEGER PRIMARY KEY NOT NULL, " +
                "title TEXT NOT NULL, " +
                "isCompleted INTEGER NOT NULL DEFAULT 0)",
        )
    }
}
