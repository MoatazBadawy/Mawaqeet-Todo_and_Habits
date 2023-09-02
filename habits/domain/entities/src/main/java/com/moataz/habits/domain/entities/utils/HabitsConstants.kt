package com.moataz.habits.domain.entities.utils

import java.util.Calendar
import java.util.Date

object HabitsConstants {
    val NEXT_RESET_DATE_HABIT: Date = Calendar.getInstance().apply {
        add(Calendar.DAY_OF_YEAR, 1)
        set(Calendar.HOUR_OF_DAY, 3)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time
}
