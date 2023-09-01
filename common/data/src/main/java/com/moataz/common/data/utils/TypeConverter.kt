package com.moataz.common.data.utils

import androidx.room.TypeConverter
import java.util.Date

class TypeConverter {
    /**
     * Converts a Long timestamp value to a Date object.
     * @param value The Long timestamp value.
     * @return A Date object or null.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Converts a Date object to a Long timestamp value.
     * @param date The Date object.
     * @return The Long timestamp value or null.
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
