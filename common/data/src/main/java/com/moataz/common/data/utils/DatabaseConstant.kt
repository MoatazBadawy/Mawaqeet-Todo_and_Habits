package com.moataz.common.data.utils

import com.moataz.todos.data.local.utils.EntityConstant.TODO_TABLE

object DatabaseConstant {
    const val DATABASE_NAME = "mawaqeet.db"
    const val DATABASE_ASSET_PATH = "database/$DATABASE_NAME"

    const val CREATE_TODO_TABLE_QUERY = """
    CREATE TABLE IF NOT EXISTS $TODO_TABLE (
        id INTEGER PRIMARY KEY NOT NULL, 
        title TEXT NOT NULL, 
        isCompleted INTEGER NOT NULL DEFAULT 0
    )
"""
}
