package com.domain.model

import androidx.annotation.Keep

@Keep
data class HabitModel(
    val id: Long,
    val name: String,
    val type: String,
    val isCompleted: Boolean
)